package com.practice.ds.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.*;

public class NodeTree {

    public class Node {
        String fName;
        String lName;
        int age;
        char gender;
        int[] birth;
        int[] death;
        Node mother;
        Node father;
        List<Node> spouse;
        List<Node> child;
        boolean tNode;

        // constructor w/ no parameters
        Node() {
            fName = "";
            lName = "";
            age = 0;
            gender = ' ';
            birth = null;
            death = null;
            mother = null;
            father = null;
            spouse = new ArrayList();
            child = new ArrayList();
        }

        // constructor w/ parameters for Names
        Node(String first, String last) {
            fName = first;
            lName = last;
            age = 0;
            gender = ' ';
            birth = null;
            death = null;
            mother = null;
            father = null;
            spouse = new ArrayList();
            child = new ArrayList();
        }

        // constructor w/ parameter for Names and Gender
        Node(String first, String last, char g) {
            fName = first;
            lName = last;
            age = 0;
            gender = g;
            birth = null;
            death = null;
            mother = null;
            father = null;
            spouse = new ArrayList();
            child = new ArrayList();
        }

        // constructor w/ parameter for Names, Gender, birth and death dates
        Node(String first, String last, char g, int[] b, int[] d) {
            fName = first;
            lName = last;
            age = calculateAge(b, d);
            gender = g;
            birth = b;
            death = d;
            mother = null;
            father = null;
            spouse = new ArrayList();
            child = new ArrayList();
        }

        // calculates age from birth date with death date or current data
        private int calculateAge(int[] b, int[] d) {
            // gets and stores current date values
            Date date = new Date();
            SimpleDateFormat dy = new SimpleDateFormat("y");
            SimpleDateFormat dm = new SimpleDateFormat("M");
            SimpleDateFormat dd = new SimpleDateFormat("d");
            int currYear = Integer.parseInt(dy.format(date));
            int currMonth = Integer.parseInt(dm.format(date));
            int currDay = Integer.parseInt(dd.format(date));

            int age;

            if (d != null) {
                age = d[2] - b[2];
                if (b[1] > d[1])
                    age--;
                else if (b[1] == d[1] && b[0] > d[0])
                    age--;
                return age;
            } else if (b != null) {
                age = currYear - b[2];
                if (b[1] > currMonth)
                    age--;
                else if (b[1] == currMonth && b[0] > currDay)
                    age--;
                return age;
            } else
                return 0;
        }
    }

    // fields for NodeTree class
    public Node root;
    public Node curr;
    public int familySize;

    // constructor for NodeTree
    public NodeTree() {
        root = null;
        curr = null;
        familySize = 0;
    }

    // creates a simple node and a tree but not spouses (Made for testing)
    public void addNode(String first, String last, char gender, String ffirst, String flast, String mfirst, String mlast) {
        Node N = new Node(first, last, gender);
        addtNode(N, false);
        Node temp;
        familySize++;
        if (familySize == 0) {
            root = N;
            curr = N;
        } else {
            temp = findPerson(root, null, first, last, true);
            if (temp != null) {
                Node temp2;
                for (int i = 0; i < temp.child.size(); i++) {
                    temp2 = temp.child.get(i);
                    addChild(N, temp2);
                    if (N.gender == 'm')
                        addFather(temp2, N);
                    else if (N.gender == 'f')
                        addMother(temp2, N);
                }
                if (temp.father != null)
                    addFather(N, temp.father);
                else
                    addFather(N, ffirst, flast);
                if (temp.mother != null)
                    addMother(N, temp.mother);
                else
                    addMother(N, mfirst, mlast);
                return;
            }
        }
        addFather(N, ffirst, flast);
        addMother(N, mfirst, mlast);
    }

    // create a full node with children and a tree (Final version)
    public void addNode(String first, String last, char gender, int[] birth, int[] death, String ffirst, String flast, String mfirst, String mlast, TupleList childs) {
        Node N = new Node(first, last, gender, birth, death);
        addtNode(N, false);
        Node temp;
        if (familySize == 0) {
            root = N;
            curr = N;
        } else {
            temp = findPerson(root, null, first, last, true);
            if (temp != null) {
                Node temp2;
                for (int i = 0; i < temp.child.size(); i++) {
                    temp2 = temp.child.get(i);
                    addChild(N, temp2);
                    if (N.gender == 'm')
                        addFather(temp2, N);
                    else if (N.gender == 'f')
                        addMother(temp2, N);
                }
                if (temp.father != null)
                    addFather(N, temp.father);
                if (temp.mother != null)
                    addMother(N, temp.mother);
            }
        }
        if (!ffirst.equals("") && !flast.equals(""))
            addFather(N, ffirst, flast);
        if (!ffirst.equals("") && !flast.equals(""))
            addMother(N, mfirst, mlast);
        if (childs != null)
            addChild(N, childs);
        familySize++;
    }

    // add m to c.mother and replaces m's temp child with real
    public void addMother(Node c, Node m) {
        c.mother = m;
        Node temp;
        for (int i = 0; i < m.child.size(); i++) {
            temp = m.child.get(i);
            if (c.fName.equals(temp.fName) && c.lName.equals(temp.lName)) {
                m.child.set(i, c);
                return;
            }
        }
        addChild(m, c);
    }

    // creates a temp mother and connects to child
    public void addMother(Node c, String mfirst, String mlast) {
        Node temp = findPerson(root, null, mfirst, mlast, true);
        if (temp == null) {
            temp = new Node(mfirst, mlast, 'f');
            addtNode(temp, true);
        } // else transfer its mother father ...
        addMother(c, temp);
    }

    // add f to c.father and replaces f's temp child with real
    public void addFather(Node c, Node f) {
        c.father = f;
        Node temp;
        for (int i = 0; i < f.child.size(); i++) {
            temp = f.child.get(i);
            if (c.fName.equals(temp.fName) && c.lName.equals(temp.lName)) {
                f.child.set(i, c);
                return;
            }
        }
        addChild(f, c);
    }

    // creates a temp father and connects to child
    public void addFather(Node c, String ffirst, String flast) {
        Node temp = findPerson(root, null, ffirst, flast, true);
        if (temp == null) {
            temp = new Node(ffirst, flast, 'm');
            addtNode(temp, true);
        } // else transfer its mother father ...
        addFather(c, temp);
    }

    // creates temp child node and connects to parent
    public void addChild(Node P, String first, String last) {
        Node C = new Node(first, last);

        // check if child already child of parent
        for (int i = 0; i < P.child.size(); i++) {
            if (first.equals(P.child.get(i).fName) && last.equals(P.child.get(i).lName)) {
                return;
            }
        }

        P.child.add(C);
        familySize++;

        // if parent has gender, associate as father or mother
        if (P.gender == 'm') {
            addFather(C, P);
        } else if (P.gender == 'f') {
            addMother(C, P);
        }
    }

    // add child to parent's child list
    public void addChild(Node P, Node C) {
        // test for in aldready child holder there
        P.child.add(C);
    }

    // takes a TupleList of children and adds children to parent if not already
    public void addChild(Node P, TupleList check) {
        Node temp;
        for (int i = 0; i < P.child.size(); i++) {
            temp = P.child.get(i);
            for (int j = 0; i < check.size(); j++) {
                if (temp.fName.equals(check.getFirst(j)) && temp.lName.equals(check.getSecond(j))) {
                    check.remove(j);
                    break;
                }
            }
        }
        for (int k = 0; k < check.size(); k++) {
            temp = findPerson(root, null, check.getFirst(k), check.getSecond(k), true);
            if (temp != null) {
                addChild(P, temp);
                if (P.gender == 'm')
                    addFather(temp, P);
                else if (P.gender == 'f')
                    addMother(temp, P);
            } else {
                temp = new Node(check.getFirst(k), check.getSecond(k));
                if (P.gender == 'm')
                    addFather(temp, P);
                else if (P.gender == 'f')
                    addMother(temp, P);
            }
        }
    }

    // adds the tag of temp node (Made for testing)
    public void addtNode(Node N, boolean temp) {
        N.tNode = temp;
    }

    // finds a node in the tree with same first and last name
    public Node findPerson(Node R, Node C, String first, String last, boolean searchP) {
        if (R == null)
            return null;
        if (R.fName.equals(first) && R.lName.equals(last)) {
            return R;
        }
        Node temp = null;
        Node child;
        if (searchP) {
            temp = findPerson(R.father, R, first, last, true);
            if (temp != null) {
                return temp;
            }
            temp = findPerson(R.mother, R, first, last, true);
            if (temp != null) {
                return temp;
            }
        }
        for (int i = 0; i < R.child.size(); i++) {
            child = R.child.get(i);
            if (C != null) {
                if (!child.fName.equals(C.fName) || !child.lName.equals(C.lName)) {
                    if (child != null)
                        temp = findPerson(R.child.get(i), R, first, last, i == 0);
                    if (temp != null) {
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    // returns root of the tree
    public Node getRoot() {
        return root;
    }

    // returns the current node of the tree
    public Node getCurr() {
        return curr;
    }

    // make curr equal to curr's mother
    public void tMother() {
        curr = curr.mother;
    }

    // make curr equal to curr's father
    public void tFather() {
        curr = curr.father;
    }

    // make curr equal to curr's child with same first and last name
    public void tChild(String fname, String lname) {
        Node temp;
        for (int i = 0; i < curr.child.size(); i++) {
            temp = curr.child.get(i);
            if (temp.fName.equals(fname) && temp.lName.equals(lname)) {
                curr = temp;
                return;
            }
        }
        // print an error message.
        System.out.println("nope");
    }

    // makes curr equal to curr's child with same index in curr's child list
    public void tChild(int index) {
        if (index < curr.child.size()) {
            curr = curr.child.get(index);
            return;
        }
        System.out.println("nope");
    }

    // returns a string with a nodes's name
    public String printName(Node p) {
        String s = p.fName + " " + p.lName;
        return s;
    }

    // returns a string with curr's name
    public String printCurrName() {
        return curr.fName + " " + curr.lName;
    }

    // returns a string with curr's age
    public String printCurrAge() {
        if (curr.age > 0) {
            return Integer.toString(curr.age);
        } else {
            return "";
        }
    }

    // returns a string with curr's birth
    public String printCurrBirth() {
        String bd = "";
        if (curr.birth != null) {
            for (int i = 0; i < 3; i++) {
                bd += Integer.toString(curr.birth[i]);
                if (i < 2) {
                    bd += "/";
                }
            }
        }
        return bd;
    }

    // returns a string with curr's death date
    public String printCurrDeath() {
        String bd = "";
        if (curr.death != null) {
            for (int i = 0; i < 3; i++) {
                bd += Integer.toString(curr.death[i]);
                if (i < 2) {
                    bd += "/";
                }
            }
        }
        return bd;
    }

    // returns string with curr's full info
    public String printCurrNode() {
        // String node = "";
        return printCurrName() + ", age: " + printCurrAge() + ", birth: " + printCurrBirth() + ", death: " + printCurrDeath();
    }

    // prints the temp flag status
    public void printTNode(Node N) {
        System.out.println(N.tNode);
    }

    // returns number of children a node has
    public int numOfChild(Node N) {
        return N.child.size();
    }

    // returns number of children curr has
    public int currNumC() {
        return curr.child.size();
    }
}

class TupleList {
    public class Tuple {
        public final String first;
        public final String second;

        public Tuple(String f, String s) {
            this.first = f;
            this.second = s;
        }

        public String getFirst() {
            return this.first;
        }

        public String getSecond() {
            return this.second;
        }
    }

    List<Tuple> tList;

    TupleList() {
        tList = new ArrayList();
    }

    public int size() {
        return tList.size();
    }

    public String getFirst(int i) {
        return tList.get(i)
            .getFirst();
    }

    public String getSecond(int i) {
        return tList.get(i)
            .getSecond();
    }

    public void add(String f, String s) {
        tList.add(new Tuple(f, s));
    }

    public void remove(int index) {
        tList.remove(index);
    }
}