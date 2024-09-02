"use client"

import { ProductCard } from "./ProductCard"

export const BestSellingProducts = ({filter}) => {

    console.log(filter)

    return (
        <div>
            <h1>Best Selling Products</h1>

            {[1, 2, 3, 4].map((product) => <ProductCard key={product} product={{ id: product, title: "Product ${product}", price: 1.99, description: "Um limão" }} />)}
        </div>
    )
}