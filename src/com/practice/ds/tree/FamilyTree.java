package com.practice.ds.tree;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class FamilyTree {

    private boolean sex; // male is true, female is false.
    private String name;
    private FamilyTree father, mother, spouse;
    private List<FamilyTree> children = new LinkedList<FamilyTree>(), siblings = new LinkedList<FamilyTree>();
    private int totalRelations = 0;
    private boolean isVisited = false;

    public FamilyTree() {

    }

    public FamilyTree(boolean sex, String name, FamilyTree father, FamilyTree mother, FamilyTree spouse, List<FamilyTree> children, List<FamilyTree> siblings) {
        this.sex = sex;
        this.name = name;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
        if (children != null)
            this.children.addAll(children);
        if (siblings != null)
            this.siblings.addAll(siblings);
        this.isVisited = false;
    }

    private int getTotalRelations() {
        // assign totalRelations
        if (father != null)
            ++totalRelations;
        if (mother != null)
            ++totalRelations;
        if (spouse != null)
            ++totalRelations;
        if (children != null)
            totalRelations += children.size();
        if (siblings != null)
            totalRelations += siblings.size();

        return totalRelations;
    }

    public boolean getSex() {
        return this.sex;
    }

    public String getName(String name) {
        return this.name;
    }

    public FamilyTree getFather() {
        return this.father;
    }

    public FamilyTree getMother() {
        return this.mother;
    }

    public FamilyTree getSpouse() {
        return this.spouse;
    }

    public List<FamilyTree> getChildrenList() {
        return this.children;
    }

    public List<FamilyTree> getSiblingsList() {
        return this.siblings;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFather(FamilyTree father) {
        this.father = father;
    }

    public void setMother(FamilyTree mother) {
        this.mother = mother;
    }

    public boolean setSpouse(FamilyTree spouse) {
        if (spouse.sex != this.sex) {
            this.spouse = spouse;
            return true;
        }
        return false;
    }

    public void setChild(FamilyTree child) {
        this.children.add(child);
    }

    public void setSibling(FamilyTree sibling) {
        this.siblings.add(sibling);
    }

    /*
     * Are the given two people related? (e.g. Uncle-nephew relationship)
     */
    public boolean areTheyRelated(FamilyTree n2) {
        return this.getRelationshipDegree(n2) >= 0;
    }

    /*
     * Are the given two people related by blood? 
     * (e.g. Brother-in-law is NOT a blood relationship)
     */
    public boolean areTheyBloodRelated(FamilyTree n2) {
        return getRelationshipDegreeHelper(n2, false) >= 0;
    }

    /*
     * helper method to return the relationship degree between two nodes.
     * considerSpouse argument is used to distinguish blood-relation vs any relation, 
     * as wife's lineage shouldn't be considered for blood-relation.
     */
    private int getRelationshipDegreeHelper(FamilyTree n2, boolean considerSpouse) {
        FamilyTree n1 = this;

        if (n1 == null || n2 == null)
            return -1;

        List<FamilyTree> queue = new LinkedList<FamilyTree>();

        queue.add(n1);
        int counter = n1.getTotalRelations() + 1;
        int degree = 0, nextCounter = 0;

        // System.out.println("counter is " + counter);

        while (!queue.isEmpty()) {
            if (counter == 0) {
                counter = nextCounter;
                nextCounter = 0;
                // System.out.println("degree is " + degree);
                degree++;
            }

            FamilyTree currNode = queue.remove(0);

            if (currNode == n2) {
                // System.out.println("in if " + degree);
                return degree;
            }
            // System.out.println("outside if " + degree);
            // comes here only if notVisited
            // search father's tree
            if (currNode.father != null && !currNode.father.isVisited) {
                queue.add(currNode.father);
                nextCounter++;
            }
            // search mother's tree
            if (currNode.mother != null && !currNode.mother.isVisited) {
                queue.add(currNode.mother);
                nextCounter++;
            }
            // search spouse's tree
            if (currNode.spouse != null && !currNode.spouse.isVisited) {
                if (considerSpouse) {
                    queue.add(currNode.spouse);
                    nextCounter++;
                }
            }
            // search children's tree
            if (currNode.children.size() > 0) {
                for (FamilyTree n : currNode.children) {
                    if (!n.isVisited) {
                        queue.add(n);
                        nextCounter++;
                    }
                }
            }
            // search siblings' tree
            if (currNode.siblings.size() > 0) {
                for (FamilyTree n : currNode.siblings) {
                    if (!n.isVisited) {
                        queue.add(n);
                        nextCounter++;
                    }
                }
            }

            // System.out.println("nextCOunter " + nextCounter);
            currNode.isVisited = true;
            counter--;
        }

        return -1;
    }

    /*
     * How far-removed are the two people in their relationships? 
     * (Parent-child relationship is a direct, degree-0 relationship, 
     * whereas Uncle-nephew is a degree-1 relationship.)
     * NOTE: if n1 == n2, then return value is -1;
     * @params: two FamilyTree object nodes
     * @return: -1 if no relationship found
     */
    public int getRelationshipDegree(FamilyTree n2) {
        return getRelationshipDegreeHelper(n2, true);
    }

    public static void main(String[] args) {
        // List<FamilyTree> children = new LinkedList<FamilyTree>();

        FamilyTree mother = new FamilyTree(false, "Diana", null, null, null, null, null);
        FamilyTree dad = new FamilyTree(true, "Evan", null, null, mother, null, null);

        FamilyTree john = new FamilyTree(true, "John", dad, mother, null, null, null);

        FamilyTree nancy = new FamilyTree(false, "Nancy", null, null, null, null, null);
        FamilyTree alex = new FamilyTree(true, "Alex", dad, mother, nancy, null, null);

        FamilyTree niki = new FamilyTree(false, "Niki", null, null, null, null, null);
        FamilyTree joe = new FamilyTree(true, "Joe", dad, mother, niki, null, null);
        FamilyTree adam = new FamilyTree(true, "Adam", null, null, null, null, null);
        FamilyTree nisha = new FamilyTree(false, "Nisha", dad, mother, adam, null, null);
        // wife1 = new FamilyTree(false, "w1", null, null, son1, null, null);
        // wife2 = new FamilyTree(false, "w2", null, null, son2, null, null);

        dad.setChild(john);
        dad.setChild(alex);
        dad.setChild(joe);
        dad.setChild(adam);

    }

}

/*public class FamilyTree {

    public static void main(String[] args) {

        Node ultimateGrandParent = new Node();
        ultimateGrandParent.setParent(null);

        ultimateGrandParent.setName("Evan");
        ultimateGrandParent.setPartner("Diana");

        Node child1 = new Node();
        child1.setName("John");
        ultimateGrandParent.addChild(child1);

        Node child2 = new Node();
        child2.setName("Alex");
        child2.setPartner("Nancy");
        ultimateGrandParent.addChild(child2);

        Node child3 = new Node();
        child3.setName("Joe");
        child3.setPartner("Niki");
        ultimateGrandParent.addChild(child3);

        Node child4 = new Node();
        child4.setName("Adam");
        child4.setPartner("Nisha");
        ultimateGrandParent.addChild(child4);

    }
}

@Getter
@Setter
class Node {

    public Node parent;
    public String name;
    public String partner;
    public Node sibling;
    public List<Node> childs;

    public boolean isLeaf() {
        return this.childs.size() == 0;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

      public void setParent(Node<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.childs.add(child);
    }

}*/