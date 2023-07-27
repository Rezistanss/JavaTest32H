package zad2;

import zad2.exception.InvalidStringContainerPatternException;

public class Main {
    public static void main(String[] args) {
        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}", true);

        st.add("02-495");
        st.add("01-120");
        st.add("05-123");
        st.add("00-000");
        //st.add("00-000");
        //st.add("ala ma kota");
        try {
            st.add("ala ma kota");
        } catch (InvalidStringContainerPatternException e) {
            System.out.println("Złap wyjątek: " + e.getMessage());
        }

        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i));
        }

        st.remove(0);
        st.remove("00-000");

        System.out.println("po usunieciu");

        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i));
        }
    }
}
