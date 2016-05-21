package by.bsu.xml.entity;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 03.04.16
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
public class PresentCandy extends Candy {
    public class Packaging {
        private String color;
        private String picture;
        private PackagingType type;

        public Packaging() {
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public void setType(PackagingType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "packaging: [" + ((color != null) ? "color=" + color : "picture=" + picture) +
                    " type=" + type + "]";
        }
    }

    private Packaging packaging = new Packaging();
    private String holiday;

    public PresentCandy() {
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    @Override
    public String toString() {
        return super.toString() + " " + packaging + " holiday=" + holiday;
    }
}
