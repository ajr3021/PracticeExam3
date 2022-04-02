package backtracking;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Testable
public class TestBacktracker {
    @Test
    public void testValidSolution(){
        ArrayList<Integer> bricks = new ArrayList<>();

        bricks.add(1);
        bricks.add(2);
        bricks.add(3);

        BrickWall brickWall = new BrickWall(bricks);

        List<Integer> validBrickSize = new ArrayList<>();
        validBrickSize.add(1);
        validBrickSize.add(2);
        validBrickSize.add(3);

        WallSolver wallSolver = new WallSolver(brickWall, validBrickSize);
        Backtracker backtracker = new Backtracker(false);
        WallSolver solution = (WallSolver) backtracker.solve(wallSolver);
        assertNotNull(solution);
    }

    @Test
    public void testInvalidSolution(){
        ArrayList<Integer> bricks = new ArrayList<>();

        bricks.add(1);
        bricks.add(2);
        bricks.add(3);

        BrickWall brickWall = new BrickWall(bricks);

        List<Integer> validBrickSize = new ArrayList<>();
        validBrickSize.add(3);

        WallSolver wallSolver = new WallSolver(brickWall, validBrickSize);
        Backtracker backtracker = new Backtracker(false);
        WallSolver solution = (WallSolver) backtracker.solve(wallSolver);
        assertNull(solution);
    }

}
