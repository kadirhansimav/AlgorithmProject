
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ozkan
 */
public class CubeSolver {

    public ArrayList<String> moves = new ArrayList<>();

    private final int DEPTH = 14;

    public String solve(CubicState state) {
        HashMap<CubicState, String> forwardParents = new HashMap<CubicState, String>();
        HashMap<CubicState, String> backwardParents = new HashMap<CubicState, String>();
        ArrayDeque<CubicState> fqueue = new ArrayDeque<CubicState>();
        ArrayDeque<CubicState> bqueue = new ArrayDeque<CubicState>();
        CubicState src = state, end = new CubicState();

        forwardParents.put(end, null);
        backwardParents.put(src, null);
        fqueue.add(end);
        fqueue.add(new CubicState());
        bqueue.add(src);

        int graphRadius = DEPTH / 2;

        for (int i = 0; i <= graphRadius; i++) {
            System.out.println(i);
            while (true) {
                if (state.stateOfCubic == null) {
                    break;
                }
                for (Entry<String, CubicState> move : src.getReachableStates().entrySet()) {
                    if (!backwardParents.containsKey(move.getValue())) {
                        backwardParents.put(move.getValue(), move.getKey());
                        bqueue.add(move.getValue());
                    }
                }
                src = bqueue.remove();

                for (Entry<String, CubicState> move : src.getReachableStates().entrySet()) {
                    if (!backwardParents.containsKey(move.getValue())) {
                        backwardParents.put(move.getValue(), move.getKey());
                        bqueue.add(move.getValue());
                    }

                    if (forwardParents.containsKey(move.getValue())) {
                        String endpath = path(move.getValue(), forwardParents);
                        String srcpath = path(move.getValue(), backwardParents);
                        srcpath = reverse(srcpath);
                        String solutionPath = srcpath + " " + endpath;
                        return solutionPath.replaceAll("(([RUF])'?) \\1", "$22");
                    }
                }
            }
        }
        return "No solution, impossible configuration";
    }

    private static String path(CubicState state, HashMap<CubicState, String> parents) {
        String path = parents.get(state);
        CubicState next = new CubicState(state.stateOfCubic);
        next.rotate(path);

        while (parents.get(next) != null) {
            path += " " + parents.get(next);
            next = new CubicState(state.stateOfCubic);
            next.rotate(path);
        }

        return path;
    }

    private static String reverse(String path) {
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

        return String.join(" ", ar);
    }
}
