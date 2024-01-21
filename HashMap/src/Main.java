public class Main {
    public static void main(String[] args) {
        MyMap<Integer, String> map = new MyMap<>(1);
        System.out.println("Size of map: " + map.size());

        map.put(1, "Sristi");
        map.put(2, "Rohit");
        map.put(3, "Ayush");

        System.out.println("Value for Sristi: " + map.get(1));
        System.out.println("Value for Rohit: " + map.get(2));
        System.out.println("Value for Ayush: " + map.get(3));

        String rohit = map.remove(2);
        System.out.println("Removed Rohit. Removed value: " + rohit);

        System.out.println("Current value of Rohit: " + map.get(2));

    }
}