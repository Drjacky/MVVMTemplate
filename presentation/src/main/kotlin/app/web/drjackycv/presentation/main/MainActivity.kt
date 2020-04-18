package app.web.drjackycv.presentation.main

import app.web.drjackycv.presentation.base.activity.BaseActivity
import app.web.drjackycv.presentation.base.fragment.BaseFragment
import app.web.drjackycv.presentation.products.productlist.ProductsListFragment

class MainActivity : BaseActivity() {

    override var childFragment: BaseFragment? = ProductsListFragment.getFragment()

}