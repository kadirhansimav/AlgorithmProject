
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ozkan
 */
public class CubeSolver {

    static final String moves[] = new String[]{"R", "RR", "U", "RU", "F", "RF","L","RL","D","RD"};
    ;
    //public ArrayList<String> moves = new ArrayList<>();

    private final int DEPTH = 14;

    static List<String> traceSteps(CubicState cs, Map<CubicState, CubicState> graph) {
        List<String> steps = new ArrayList<>();
        while (cs != null) {
            steps.add(moves[cs.lastMove]);
            cs = graph.get(cs);
        }
        steps.remove(steps.size() - 1);
        Collections.reverse(steps);
        return steps;
    }

    public List<String> solveBFS(CubicState state) {
        Map<CubicState, CubicState> prev = new HashMap<>();
        Queue<CubicState> q = new LinkedList<>();
        int i = 0;
        q.add(state);
        prev.put(state, null);

        while (!q.isEmpty()) {
            CubicState curr = q.remove();

            if (checkSolved(curr)) {
                System.out.println("Solved");
                return traceSteps(curr, prev);
            }
            for (CubicState neigh : curr.neighbours()) {
                if (!prev.containsKey(neigh)) {
                    prev.put(neigh, curr);
                    q.add(neigh);
                }
            }
        }
        return null;
    }

    public List<String> solveBFSs(CubicState state) {
        Map<CubicState, CubicState> prev = new HashMap<>();
        Queue<CubicState> q = new LinkedList<>();
        int i = 0;
        q.add(state);
        prev.put(state, null);

        while (!q.isEmpty()) {
            CubicState curr = q.poll();

            if (checkSolved(curr)) {
                System.out.println("Solved");
                return traceSteps(curr, prev);
            }
            for (CubicState neigh : curr.neighbours()) {
                if (!prev.containsKey(neigh)) {
                    prev.put(neigh, curr);
                    q.add(neigh);
                }
            }
        }
        return null;
    }

    public String solve(CubicState state) {
        HashMap<CubicState, String> forwardParents = new HashMap<CubicState, String>();
        HashMap<CubicState, String> backwardParents = new HashMap<CubicState, String>();
        ArrayDeque<CubicState> fqueue = new ArrayDeque<CubicState>();
        ArrayDeque<CubicState> bqueue = new ArrayDeque<CubicState>();
        CubicState src = state, end = new CubicState();

        forwardParents.put(end, null);
        backwardParents.put(src, null);
        fqueue.add(end);
        fqueue.add(new CubicState(true));
        bqueue.add(src);

        if (end.equals(src)) {
            return "Already solved";
        }

        int graphRadius = DEPTH / 2;

        for (int i = 0; i <= graphRadius; i++) {
            while (true) {
                end = fqueue.remove();
                if (end.isNullState) {
                    fqueue.add(new CubicState(true));
                    break;
                }
                for (Entry<String, CubicState> move : src.getReachableStates().entrySet()) {
                    if (!backwardParents.containsKey(move.getValue())) {
                        forwardParents.put(move.getValue(), move.getKey());
                        fqueue.add(move.getValue());
                    }
                }
                src = bqueue.remove();

                for (Entry<String, CubicState> move : src.getReachableStates().entrySet()) {
                    if (!backwardParents.containsKey(move.getValue())) {
                        backwardParents.put(move.getValue(), move.getKey());
                        bqueue.add(move.getValue());
                    }

                    if (forwardParents.containsKey(move.getValue())) {
                        System.out.println("here");
                        String endpath = path(move.getValue(), forwardParents);
                        System.out.println("sa");
                        String srcpath = path(move.getValue(), backwardParents);
                        srcpath = reverse(srcpath);
                        String solutionPath = srcpath + " " + endpath;
                        System.out.println("Solved");
                        return solutionPath.replaceAll("(([RUF])'?) \\1", "$22");
                    }
                }
            }
        }
        return "No solution, impossible configuration";
    }

    private String path(CubicState state, HashMap<CubicState, String> parents) {
        String path = parents.get(state);
        CubicState next = new CubicState(state.stateOfCubic);
        next.rotate(path);

        while (parents.get(next) != null) {
            System.out.println("parent-get:" + parents.get(next) + "  ----> next:" + next.stateOfCubic[0][0]);
            path += " " + parents.get(next);
            next = new CubicState(state.stateOfCubic);
            next.rotate(path);
        }

        return path;
    }

    private String reverse(String path) {
        path += " ";
        String reverse = "";

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == ' ') {
                reverse += "' ";
            } else if (path.charAt(i) != '\'') {
                reverse += path.charAt(i);
            } else {
                reverse += " ";
                i++;
            }
        }

        String ar[] = reverse.split(" ");
        for (int i = 0; i < ar.length / 2; i++) {
            String temp = ar[i];
            ar[i] = ar[ar.length - 1 - i];
            ar[ar.length - 1 - i] = temp;
        }

        System.out.println("Pathhhhh: " + path);
        return String.join(" ", ar);
    }

    private boolean compareTo(HashMap<CubicState, String> hashmap, CubicState stateObject) {
        for (Entry<CubicState, String> element : hashmap.entrySet()) {
            if (stateObject.equals(element.getKey())) {
                return true;
            }
        }
        return false;
    }

    private String ownGet(HashMap<CubicState, String> hashmap, CubicState stateObject) {
        for (Entry<CubicState, String> element : hashmap.entrySet()) {
            if (stateObject.equals(element.getKey())) {
                return element.getValue();
            }
        }
        return null;
    }

    public boolean checkSolved(CubicState state) {
        CubicState solved = new CubicState();
        //System.out.println("here");
      
        /*for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
             //   System.out.print((solved.stateOfCubic[i][j]) + " ");
            }
        }
        System.out.println("");
*/
        if (solved.equals(state)) {
            return true;
        }
        return false;
    }
}
