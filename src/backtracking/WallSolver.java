package backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WallSolver implements Configuration{
    private final BrickWall brickWall;
    private final List<Integer> validBrickSizes;

    public WallSolver(BrickWall brickWall, List<Integer> validBrickSizes) {
        this.brickWall = brickWall;
        this.validBrickSizes = validBrickSizes;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();
        for(Integer brickSize : validBrickSizes){
            if(brickWall.addBrick(brickSize)){
                BrickWall brickWallCopy = brickWall.brickWallCopy();
                successors.add(new WallSolver(brickWallCopy, validBrickSizes));
            }
        }

        return successors;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isGoal() {
        return brickWall.isComplete();
    }

    public BrickWall getBrickWall() {
        return brickWall;
    }
}
