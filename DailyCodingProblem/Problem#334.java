/*

Problem Statement:

The 24 game is played as follows. You are given a list of four integers, each between 1 and 9, in a fixed order. By placing the operators +, -, *, and / between the numbers, and grouping them with parentheses, determine whether it is possible to reach the value 24.

For example, given the input [5, 2, 7, 8], you should return True, since (5 * 2 - 7) * 8 = 24.

Write a function that plays the 24 game.


Approach:
- Since all the numbers and patterns needs to be evaluated, we will need to choose one operation evaluate the result and reset the state.
- So we will need to evaluate all the operation, and we will need to backtrack on our answer.
- Since we will be dealing with integer division we will keep one integer precision with us.

*/

public class Problem334 {
    private static final double error = 0.01;
    private static final double target = 24.0;
    public boolean solve(int[] cards ) {
        List<Double> nums = new ArrayList<>();
        for(int x: nums ) {
            nums.add((double)x);
        }
        return helper(nums);
    }

    private boolean helper(List<Double> nums) {
        if(nums.size() == 1) {
            if(Math.abs(nums.get(0) - target) <= error ) {
                return true;
            }
        }

        for(int i=0;i<nums.size();i++) {
            for(int j=0;j<i;j++) {
                Double val1 = nums.get(i);
                Double val2 = nums.get(j);
                List<Double> next = new ArrayList<>();
                next.add(val1+val2);
                next.add(val1 - val2);
                next.add(val2 - val1);
                next.add(val1*val2);
                if(val1!=0) {
                    next.add(val2/val1);
                }
                if(val2!=0) {
                    next.add(val1/val2);
                }
                nums.remove(i);
                nums.remove(j);
                for(Double val: next) {
                    nums.add(val);
                    if(helper(nums)) {
                        return true;
                    }
                    nums.removeLast();
                }
                nums.add(j, val2);
                nums.add(i, val1);
            }
        }

        return false;
    }
}