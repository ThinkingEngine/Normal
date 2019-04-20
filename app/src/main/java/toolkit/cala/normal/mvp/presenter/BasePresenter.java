package toolkit.cala.normal.mvp.presenter;

import toolkit.cala.normal.mvp.IModel;
import toolkit.cala.normal.mvp.IView;

/**
 * package name:toolkit.cala.normal.mvp.presenter
 * create:cala
 * date:2019/4/20
 * description:
 */
public abstract class BasePresenter<M extends IModel, V extends IView> implements IPresenter<V, M> {

    V mView;
    M mModel;

    public BasePresenter() {
        mModel = createModel();
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public V getView() {
        return mView;
    }

    @Override
    public void attachModel(M model) {
        mModel = model;
    }

    @Override
    public void detachModel() {
        mModel = null;
    }

    public abstract M createModel();

    public M getModel() {
        return mModel;
    }
}
