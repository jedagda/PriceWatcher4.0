public interface Trigger {
    public void addItem();
    public void checkPrice();
    public void about();
    public void exit();
    public void setListener(Listener listener);
    public ConfigureUI getUI();

}
