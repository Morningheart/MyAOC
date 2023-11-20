package year2021.day06.codes;

class LanternFish {
    private int age;
    private boolean hasProducedNewFish;

    public LanternFish(int age) {
        this.age = age;
    }

    public void age() {
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