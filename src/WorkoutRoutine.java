

public class WorkoutRoutine {
    private String routineName;
    private int durationInMinutes;
    private int caloriesBurned;

    public WorkoutRoutine(String routineName, int durationInMinutes, int caloriesBurned) {
        this.routineName = routineName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = caloriesBurned;
    }

    public String getRoutineName() {
        return routineName;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public double getCaloriesPerMinute() {
        return (double) caloriesBurned / durationInMinutes;
    }

    @Override
    public String toString() {
        return String.format("Routine: %-20s | Duration: %3d min | Calories: %4d kcal", routineName, durationInMinutes, caloriesBurned);
    }
}