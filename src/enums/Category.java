package enums;

public enum Category {
    Pizza,
    Drink,
    PastaRice,
    Appetizer,
    Desert;

    @Override
    public String toString() {
        switch (this){
            case PastaRice: return "Pasta & Rice";
        }
        return null;
    }
}
