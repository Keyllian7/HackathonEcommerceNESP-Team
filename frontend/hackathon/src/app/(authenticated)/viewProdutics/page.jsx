"use client";

import React from "react";
//import { useSearchParams } from "next/navigation";
import { BackBar } from "../burguerlist/components/BackBar";
import { OptionsBarHome } from "../burguerlist/components/OptionsBarHome";
import { ProductDescription } from "./components/ProductDescription";
import { Items } from "../(Items)/components/Items";

export default function VisualizarProduto() {
  //const searchParams = useSearchParams();
  //const id = searchParams.get("id");

  const allItems = [...Items.burguer, ...Items.bebidas];
  //const itemSelecionado = allItems.find((item) => item.id === id);

  if (!itemSelecionado) {
    return <div>Produto não encontrado</div>;
  }

  return (
    <div className="flex flex-col min-h-screen">
      <main className="flex-grow">
        <BackBar />
        <ProductDescription item={itemSelecionado} />{" "}
      </main>
      <OptionsBarHome />
    </div>
  );
}
