package com.imoond.data.models.productModels

import Attribute
import Category
import DefaultAttribute
import Dimensions


data class ProductModelItem(
    val _links: Links?,
    val attributes: List<Attribute>?,
    val average_rating: String,
    val backordered: Boolean,
    val backorders: String,
    val backorders_allowed: Boolean,
    val button_text: String,
    val catalog_visibility: String,
    val categories: List<Category>,
    val cross_sell_ids: List<Any>?,
    val date_created: String,
    val date_created_gmt: String,
    val date_modified: String,
    val date_modified_gmt: String,
    val date_on_sale_from: Any,
    val date_on_sale_from_gmt: Any,
    val date_on_sale_to: Any,
    val date_on_sale_to_gmt: Any,
    val default_attributes: List<DefaultAttribute>?,
    val description: String,
    val dimensions: Dimensions?,
    val download_expiry: Int,
    val download_limit: Int,
    val downloadable: Boolean,
    val downloads: List<Any>,
    val external_url: String,
    val featured: Boolean,
    val grouped_products: List<Any>?,
    val id: Int,
    val images: List<Image>,
    val manage_stock: Boolean,
    val menu_order: Int,
    val meta_data: List<Any>?,
    val name: String,
    val on_sale: Boolean,
    val parent_id: Int,
    val permalink: String,
    val price: String,
    val price_html: String,
    val purchasable: Boolean,
    val purchase_note: String,
    val rating_count: Int,
    val regular_price: String,
    val related_ids: List<Int>?,
    val reviews_allowed: Boolean,
    val sale_price: String,
    val shipping_class: String,
    val shipping_class_id: Int,
    val shipping_required: Boolean,
    val shipping_taxable: Boolean,
    val short_description: String,
    val sku: String,
    val slug: String,
    val sold_individually: Boolean,
    val status: String,
    val stock_quantity: Any,
    val stock_status: String,
    val tags: List<Any>?,
    val tax_class: String,
    val tax_status: String,
    val total_sales: Int,
    val type: String,
    val upsell_ids: List<Any>?,
    val variations: List<Any>?,
    val virtual: Boolean,
    val weight: String
)