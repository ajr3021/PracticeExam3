package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrickWall {
    private final List<Integer> wall;
    private final List<Boolean> canPlace = new ArrayList<>();
    private List<Integer> secondLayer = new ArrayList<>();
    private int totalSecondLength = 0;
    private int totalFirstLength = 0;

    public BrickWall(List<Integer> wall) {
        this.wall = wall;

        canPlace.add(false); // can't place anything at the zeroth position because that would be placing a brick of length 0

        for(Integer value : this.wall){
            for(int i = totalFirstLength + 1; i < totalFirstLength + value; i++){
                canPlace.add(true);
            }
            canPlace.add(false);
            totalFirstLength += value;
        }

        canPlace.set(canPlace.size() - 1, true); // you can always place at the very end because if you couldn't, nothing would be valid;
    }

    public BrickWall brickWallCopy(){
        List<Integer> wall = new ArrayList<>(this.wall);
        List<Integer> secondLayer = new ArrayList<>(this.secondLayer);
        BrickWall brickWall = new BrickWall(wall);
        brickWall.setSecondLayer(secondLayer, this.totalSecondLength);
        return brickWall;
    }

    private void setSecondLayer(List<Integer> secondLayer, int size){
        this.secondLayer = secondLayer;
        this.totalSecondLength = size;
    }

    public boolean addBrick(Integer brickLength){
        if(totalSecondLength + brickLength <= totalFirstLength && canPlace.get(totalSecondLength + brickLength)){
            secondLayer.add(brickLength);
            this.totalSecondLength += brickLength;
            return true;
        }else{
            return false;
        }
    }

    private boolean isValidWall(){
        int index = 0;
        for(Integer brickLength: secondLayer){
            index += brickLength;
            if(!canPlace.get(index)){
                return false;
            }
        }
        return true;
    }

    public boolean isComplete(){
        return totalSecondLength == totalFirstLength && isValidWall();
    }

    public List<Integer> getWall() {
        return wall;
    }

    public List<Boolean> getCanPlace() {
        return canPlace;
    }

    public List<Integer> getSecondLayer() {
        return secondLayer;
    }

    public int getTotalSecondLength() {
        return totalSecondLength;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(" ");
        for(int i = 0; i < totalSecondLength; i++){
            stringBuilder.append("_ ");
        }
        stringBuilder.append(totalSecondLength == 0 ? "" : "\n|");

        for(Integer brickLength: secondLayer){
            for(int i = 0; i < brickLength - 1; i++){
                stringBuilder.append("  ");
            }
            stringBuilder.append(" |");
        }

        stringBuilder.append("\n ");

        for(int i = 0; i < totalFirstLength; i++){
            stringBuilder.append("_ ");
        }
        stringBuilder.append(" \n|");

        for(Integer brickLength: wall){
            for(int i = 0; i < brickLength - 1; i++){
                stringBuilder.append("  ");
            }
            stringBuilder.append(" |");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayList<Integer> bricks = new ArrayList<>();

        bricks.add(3);
        bricks.add(1);
        bricks.add(2);
        bricks.add(3);



        BrickWall brickWall = new BrickWall(bricks);

        System.out.println(Arrays.toString(brickWall.getCanPlace().toArray()));

        System.out.println("Original Wall: ");
        System.out.println(brickWall);

        List<Integer> validBrickSize = new ArrayList<>();
        validBrickSize.add(1);
        validBrickSize.add(2);
        validBrickSize.add(3);
        WallSolver wallSolver = new WallSolver(brickWall, validBrickSize);
        Backtracker backtracker = new Backtracker(false);
        WallSolver solution = (WallSolver) backtracker.solve(wallSolver);

        System.out.println("Solution");
        System.out.println(solution != null ? solution.getBrickWall() : "No Solutions");
    }
}
