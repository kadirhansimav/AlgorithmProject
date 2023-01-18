package com.mycompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author PC
 */
public class CubicState {

    

    char[][] stateOfCubic;
    boolean isNullState;
    int lastMove;
/**
 * this function created for bfs algorithm we just find the states neighbours
 * @return neigbours
 */
    List<CubicState> neighbours() {
        List<CubicState> neigh = new ArrayList<>();
        neigh.add(new CubicState(permApply(F),4));
        neigh.add(new CubicState(permApply(RF),5));
        neigh.add(new CubicState(permApply(R),0));
        neigh.add(new CubicState(permApply(RR),1));
        neigh.add(new CubicState(permApply(U),2));
        neigh.add(new CubicState(permApply(RU),3));
        neigh.add(new CubicState(permApply(L),6));
        neigh.add(new CubicState(permApply(RL),7));
        neigh.add(new CubicState(permApply(D),8));
        neigh.add(new CubicState(permApply(RD),9));
        
        return neigh;
    }
/**
 * constructor with state
 * @param state is state null
 */
    public CubicState(boolean state) {
        this.isNullState = state;
    }
/**
 * constructor with state and last move
 * @param state current cubic state
 * @param lastMove last move 
 */
    public CubicState(char[][] state, int lastMove){
        this.stateOfCubic = state;
        this.lastMove = lastMove;
    }
    /**
     * constructor with given state
     * @param state prepared state
     */
    public CubicState(char[][] state) {
        this.stateOfCubic = state;
        this.isNullState = false;
    }
/**
 * default constructor
 */
    public CubicState() {
        stateOfCubic = new char[6][4];
        for (int i = 0; i < stateOfCubic.length; i++) {
            for (int j = 0; j < stateOfCubic[i].length; j++) {
                switch (i) {
                    case 0:
                        stateOfCubic[i][j] = 'r';
                        break;
                    case 1:
                        stateOfCubic[i][j] = 'w';
                        break;
                    case 2:
                        stateOfCubic[i][j] = 'g';
                        break;
                    case 3:
                        stateOfCubic[i][j] = 'y';
                        break;
                    case 4:
                        stateOfCubic[i][j] = 'b';
                        break;
                    default:
                        stateOfCubic[i][j] = 'o';
                        break;
                }
            }
        }
        this.isNullState = false;
    }
    /*Moves 
    *F = front
    *RF = counterclockwise front
    *R = right
    *RR = counterclockwise right
    *L = left
    *RL = counterclockwise left
    *D = down
    *RD = counterclockwise down
    *B = back
    *RB = counterclockwise back
    *U = Up
    *RU = counterclockwise up
    */
    private int[] F = {0, 1, 19, 17, 6, 4, 7, 5, 22, 20, 10, 11, 12, 13, 14, 15, 16, 8, 18, 9, 2, 21, 3, 23};
    private int[] RF = {0, 1, 20, 22, 5, 7, 4, 6, 17, 19, 10, 11, 12, 13, 14, 15, 16, 3, 18, 2, 9, 21, 8, 23};
    private int[] R = {0, 5, 2, 7, 4, 9, 6, 11, 8, 13, 10, 15, 12, 1, 14, 3, 16, 17, 18, 19, 22, 20, 23, 21};
    private int[] RR = {0, 13, 2, 15, 4, 1, 6, 3, 8, 5, 10, 7, 12, 9, 14, 11, 16, 17, 18, 19, 21, 23, 20, 22};
    private int[] L = {12, 1, 14, 3, 0, 5, 2, 7, 4, 9, 6, 11, 8, 13, 10, 15, 18, 16, 19, 17, 20, 21, 22, 23};
    private int[] RL = {4, 1, 6, 3, 8, 5, 10, 7, 12, 9, 14, 11, 0, 13, 2, 15, 17, 19, 16, 18, 20, 21, 22, 23};
    private int[] D = {0, 1, 2, 3, 4, 5, 18, 19, 10, 8, 11, 9, 23, 22, 14, 15, 16, 17, 13, 12, 20, 21, 6, 7};
    private int[] RD = {0, 1, 2, 3, 4, 5, 22, 23, 9, 11, 8, 10, 19, 18, 14, 15, 16, 17, 6, 7, 20, 21, 13, 12};
    private int[] B = {21, 23, 2, 3, 4, 5, 6, 7, 8, 9, 16, 18, 14, 12, 15, 13, 1, 17, 0, 19, 20, 11, 22, 10};
    private int[] RB = {18, 16, 2, 3, 4, 5, 6, 7, 8, 9, 23, 21, 13, 15, 12, 14, 10, 17, 11, 19, 20, 0, 22, 1};
    private int[] U = {2, 0, 3, 1, 20, 21, 6, 7, 8, 9, 10, 11, 12, 13, 17, 16, 4, 5, 18, 19, 15, 14, 22, 23};
    private int[] RU = {1, 3, 0, 2, 16, 17, 6, 7, 8, 9, 10, 11, 12, 13, 21, 20, 15, 14, 18, 19, 4, 5, 22, 23};

    /**
     * rotate the cube function
     * @param rotateDirection rotating direction
     */
    public void rotate(String rotateDirection) {
        char[][] copyOfState = new char[6][4];
        for (int i = 0; i < stateOfCubic.length; i++) {
            System.arraycopy(stateOfCubic[i], 0, copyOfState[i], 0, stateOfCubic[i].length);
        }
        switch (rotateDirection) {
            case "F" -> {
                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[F[i] / 4][F[i] % 4];
                }
                break;
            }
            case "RF" -> {
                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[RF[i] / 4][RF[i] % 4];
                }
                break;
            }
            case "R" -> {
                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[R[i] / 4][R[i] % 4];
                }
                break;
            }
            case "RR" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[RR[i] / 4][RR[i] % 4];
                }
                break;
            }
            case "L" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[L[i] / 4][L[i] % 4];
                }
                break;
            }
            case "RL" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[RL[i] / 4][RL[i] % 4];
                }
                break;
            }
            case "D" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[D[i] / 4][D[i] % 4];
                }
                break;
            }
            case "RD" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[RD[i] / 4][RD[i] % 4];
                }
                break;
            }
            case "B" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[B[i] / 4][B[i] % 4];
                }
                break;
            }
            case "RB" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[RB[i] / 4][RB[i] % 4];
                }
                break;
            }
            case "U" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[U[i] / 4][U[i] % 4];
                }
                break;
            }
            case "RU" -> {

                for (int i = 0; i < F.length; i++) {
                    stateOfCubic[i / 4][i % 4] = copyOfState[RU[i] / 4][RU[i] % 4];
                }
                break;
            }

        }
    }

 /**
  * rotating cube function 
  * @param perm moves 
  * @return new position
  */
    public char[][] permApply(int[] perm) {
        char[][] newPositions = new char[6][4];
        for (int i = 0; i < perm.length; i++) {
            newPositions[i / 4][i % 4] = stateOfCubic[perm[i] / 4][perm[i] % 4];
        }
        return newPositions;
    }

   
   
    @Override
    public int hashCode() {
        return java.util.Arrays.deepHashCode( stateOfCubic );
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().getSimpleName().equals("CubicState")) {
            return false;
        }
        CubicState state = (CubicState) obj;
        if (state.stateOfCubic.length != this.stateOfCubic.length) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.stateOfCubic[i][j] != state.stateOfCubic[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}