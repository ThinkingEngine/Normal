package toolkit.cala.normal.mvp.presenter;

import toolkit.cala.normal.mvp.IModel;
import toolkit.cala.normal.mvp.IView;

/**
 * package name:toolkit.cala.normal.mvp.presenter
 * create:cala
 * date:2019/4/20
 * description:
 */
public interface IPresenter<V extends IView,M extends IModel> {

    void attachView(V view);

    void detachView();

    void attachModel(M model);

    void detachModel();
}
