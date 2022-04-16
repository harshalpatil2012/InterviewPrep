package codeInterView.ood.callCenter;

import java.util.ArrayList;

/*Imagine you have a call center with three levels of employees: respondent, manager, and director. 
An incoming telephone call must be first allocated to a respondent who is free. 
If the respondent can't handle the call, he or she must escalate the call to a manager.
If the manager is not free or not able to handle it, then the call should be escalated to a director.
Design the classes and data structures for this problem.
Implement a method dispatchCall() which assigns a call to the first available employee.
*/

//route the calls to the correct person
public class CallHandler {
    private static CallHandler instance;
    private final int LEVELS = 3;
    private final int NUM_RESPONDENTS = 8;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;
    // list of employees by level
    ArrayList<ArrayList<Employee>> employeeLevels;
    ArrayList<ArrayList<Call>> callQueues;

    public static CallHandler getInstance() {
        if (instance == null) {
            instance = new CallHandler();
        }
        return instance;
    }

    public CallHandler() {
        employeeLevels = new ArrayList<ArrayList<Employee>>(LEVELS);
        callQueues = new ArrayList<ArrayList<Call>>(LEVELS);
        ArrayList<Employee> respondents = new ArrayList<Employee>(NUM_RESPONDENTS);
        for (int i = 0; i < NUM_RESPONDENTS; i++) {
            respondents.add(new Respondent());
        }
        employeeLevels.add(respondents);
        ArrayList<Employee> managers = new ArrayList<Employee>(NUM_MANAGERS);
        managers.add(new Manager());
        employeeLevels.add(managers);

        ArrayList<Employee> directors = new ArrayList<Employee>(NUM_DIRECTORS);
        directors.add(new Director());
        employeeLevels.add(directors);
    }

    public boolean assignCall(Employee emp) {
        // get the highest rank this employee can serve
        for (int rank = emp.getRank()
            .getValue(); rank >= 0; rank--) {
            ArrayList<Call> queue = callQueues.get(rank);

            if (queue.size() > 0) {
                Call call = queue.remove(0);
                if (call != null) {
                    // receive call
                    return true;
                }
            }
        }
        return false;
    }

    public Employee setHandlerForCall(Call call) {
        for (int level = call.getRank()
            .getValue(); level < LEVELS; level++) {
            ArrayList<Employee> employeeLevel = employeeLevels.get(level);
            for (Employee e : employeeLevel) {
                if (e.isFree())
                    return e;
            }
        }
        return null;
    }

    public void dispatchCall(Call call) {
        Employee e = setHandlerForCall(call);
        if (e != null) {
            e.receiveCall(call);
            call.setHandler(e);
        } else {
            call.reply("please wait for the next free employee");
            callQueues.get(call.getRank()
                .getValue())
                .add(call);
        }
    }

    public void dispatchCaller(Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }
}

enum Rank {
    Responder(0), Manager(1), Director(2);

    private int value;

    private Rank(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}

class Caller {
    private String name;
    private int userId;

    public Caller(int id, String nm) {
        userId = id;
        name = nm;
    }
}

// represents a call from a user. it has a minimum rank and is assigned to the first emplyee who can handle it
class Call {
    private Rank rank;
    private Caller caller;
    private Employee handler;

    public Call(Caller c) {
        rank = Rank.Responder;
        caller = c;
    }

    public void setHandler(Employee e) {
        handler = e;
    }

    public void reply(String message) {
        System.out.println(message);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank r) {
        rank = r;
    }

    public Rank incrementRank() {
        if (rank == Rank.Responder) {
            rank = Rank.Manager;
        } else if (rank == Rank.Manager) {
            rank = Rank.Director;
        }
        return rank;
    }

    public void disconnect() {
        reply("thanks for calling");
    }
}

class Respondent extends Employee {
    public Respondent() {
        rank = Rank.Responder;
    }
}

abstract class Employee {
    protected Call currentCall = null;
    protected Rank rank;

    public Employee() {

    }

    public void receiveCall(Call call) {
        currentCall = call;
    }

    public void callFinished() {
        if (currentCall != null) {
            currentCall.disconnect();
            currentCall = null;
        }
        assignNewCall();
    }

    public void escalateAndReassign() {
        if (currentCall != null) {
            currentCall.incrementRank();
            // assign the current call
            CallHandler.getInstance()
                .dispatchCall(currentCall);
            currentCall = null;
        }
        assignNewCall();
    }

    public boolean assignNewCall() {
        if (!isFree()) {
            return false;
        }
        return CallHandler.getInstance()
            .assignCall(this);
    }

    public boolean isFree() {
        return currentCall == null;
    }

    public Rank getRank() {
        return rank;
    }
}

class Manager extends Employee {
    public Manager() {
        rank = Rank.Manager;
    }
}

class Director extends Employee {
    public Director() {
        rank = Rank.Director;
    }
}