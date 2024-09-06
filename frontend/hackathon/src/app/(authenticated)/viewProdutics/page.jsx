import React from "react";
import { BackBar } from "../burguerlist/components/BackBar";
import { OptionsBarHome } from "../burguerlist/components/OptionsBarHome";
import { ProductDescription } from "./components/ProductDescription";

export default function VisualizarProduto(){
    return(
        <div className="flex flex-col min-h-screen"> 
            <main className="flex-grow">
                <BackBar/>
                <ProductDescription/>
            </main>
            <OptionsBarHome/>
        </div>
    );
}