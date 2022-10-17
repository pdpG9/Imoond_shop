package com.imoond.data.data_source

import com.imoond.data.models.productModels.ProductModelItem
import com.imoond.data.untils.DataPageLoader

class ProductSource( loader: DataPageLoader<ProductModelItem>, pageSize:Int):BaseDataSource<ProductModelItem>(loader, pageSize)