package com.mycompany;


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
    
    

    private final int DEPTH = 14;

    List<String> traceSteps(CubicState cs, Map<CubicState, CubicState> graph) {
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



    public boolean checkSolved(CubicState state) {
        CubicState solved = new CubicState();
        for(int i = 0 ; i<6 ; i++){
            char color = state.stateOfCubic[i][0];
            for (int j = 0; j < 4; j++) {
                if(color != state.stateOfCubic[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
