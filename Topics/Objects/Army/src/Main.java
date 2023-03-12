class Army {

    public static void createArmy() {
        Unit unitJim = new Unit("Jim");
        Unit unitBreb = new Unit("Breb");
        Unit unitFug = new Unit("Fug");
        Unit unitStimp = new Unit("Stimp");
        Unit unitPreem = new Unit("Preem");

        Knight knightJoddles = new Knight("Joddles");
        Knight knightPeeper = new Knight("Peeper");
        Knight knightSlopper = new Knight("Slopper");

        General generalScredds = new General("Scredds");

        Doctor doctorSlempy = new Doctor("Slempy");

    }


    // Don't change the code below
    static class Unit {
        static String nameUnit;
        static int countUnit;

        public Unit(String name) {
            countUnit++;
            nameUnit = name;

        }
    }

    static class Knight {
        static String nameKnight;
        static int countKnight;

        public Knight(String name) {
            countKnight++;
            nameKnight = name;

        }
    }

    static class General {
        static String nameGeneral;
        static int countGeneral;

        public General(String name) {
            countGeneral++;
            nameGeneral = name;

        }
    }

    static class Doctor {
        static String nameDoctor;
        static int countDoctor;

        public Doctor(String name) {
            countDoctor++;
            nameDoctor = name;

        }
    }

    public static void main(String[] args) {
        createArmy();
        System.out.println(Unit.countUnit);
        System.out.println(Knight.countKnight);
        System.out.println(General.countGeneral);
        System.out.println(Doctor.countDoctor);
    }

}