package year2021.day06.codes;

import lombok.Getter;

class LanternFish {
    @Getter
    private int age;
    private boolean hasProducedNewFish;

    public LanternFish(int age) {
        this.age = age;
    }

    public void oneDayPasses() {
        hasProducedNewFish = (age == 0); // Indicating if the fish has produced a new fish
        age = age == 0 ? 6 : --age;
    }

    public boolean hasProducedNewFish() {
        return hasProducedNewFish;
    }

    @Override
    public String toString() {
        return String.valueOf(age);
    }
}

// Initial state: 3,4,3,1,2
// After  1 day:  2,3,2,0,1
// After  2 days: 1,2,1,6,0,8