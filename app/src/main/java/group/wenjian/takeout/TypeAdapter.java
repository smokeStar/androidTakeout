package group.wenjian.takeout;

public class TypeAdapter extends BaseAdapter<Type>{

    HomeActivity home;

    public TypeAdapter(HomeActivity home) {
        super(home, R.layout.item_type, group.wenjian.takeout.BR.type, BR.methods);
        this.home = home;
        this.methods = new Methods();
        this.setList(dd.g.types);
    }

    public class Methods{

        public void OnItemClick(Type type) {

            for(int i = 0 ; i < getItemCount(); i++){
                Type t = getItem(i);
                if( type == t){
                    t.setChoose(true);
                }else{
                    t.setChoose(false);
                }
            }
            home.menuAdapter.setType(type);

        }

    }
}

