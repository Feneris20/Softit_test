package beans;

import model.NewspaperInfo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADOBNER on 2018-08-12.
 */
@ManagedBean
@SessionScoped
public class NewspaperInfoMock {

    private static List<NewspaperInfo> newspaperInfos = new ArrayList<>();

    public void insertNewspaperInfo(NewspaperInfo newspaperInfo){
        newspaperInfos.add(newspaperInfo);
    }

    public List<NewspaperInfo> getNewspaperInfos(){
        return newspaperInfos;
    }

}
