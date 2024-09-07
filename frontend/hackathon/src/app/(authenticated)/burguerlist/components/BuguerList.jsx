"use client";

import Image from "next/image";
import { adicionarAosFavoritos } from "../../utils/adicionarFavorites";
import { Items } from "../../(Items)/components/Items";
import { useState, useEffect } from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Link from "next/link";

export const BurguerList = () => {
  const [favoritos, setFavoritos] = useState([]);
  const categoriaSelecionada = "burguer";
  const selecionarItems = Items[categoriaSelecionada] || [];

  useEffect(() => {
    const favoritosSalvos = JSON.parse(localStorage.getItem("favoritos")) || [];
    setFavoritos(favoritosSalvos);
  }, []);

  return (
    <section>
      <ToastContainer />
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 bg-quaternary p-6">
        {Array.isArray(selecionarItems) && selecionarItems.length > 0 ? (
          selecionarItems.map((Item) => (
            <div
              key={Item.id}
              className="bg-white p-4 rounded-lg flex flex-col items-center"
            >
              <Image
                src={Item.imagem}
                alt={Item.nome}
                width={150}
                height={150}
                className="w-full h-24 object-contain mb-2 rounded-lg"
              />
              <h2 className="mt-2 text-lg font-semibold text-center">
                {Item.nome}
              </h2>
              <p className="text-lg font-bold mt-1 text-center">{Item.preço}</p>
              <div className="flex flex-col sm:flex-row items-center justify-between w-full mt-4">
                <Link href={`/viewProdutics?id=${Item.id}`}>
                  <button className="bg-primary text-white px-4 py-2 rounded-md border border-transparent hover:bg-secondary transition-colors duration-300 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-opacity-50 text-sm sm:text-base w-full sm:w-auto">
                    Ver Detalhes
                  </button>
                </Link>
                <Image
                  className="h-6 w-6 cursor-pointer mt-2 sm:mt-0 ml-0 sm:ml-2"
                  src="/images/heart.png"
                  alt="Favoritar"
                  width={24}
                  height={24}
                  onClick={() =>
                    adicionarAosFavoritos(Item, favoritos, setFavoritos)
                  }
                />
              </div>
            </div>
          ))
        ) : (
          <p className="text-center">Nenhum Burguer Cadastrado</p>
        )}
      </div>
      <div className="flex justify-center mt-6">
        <button className="bg-gradient-to-r from-primary to-secondary text-white font-semibold py-2 px-4 rounded-lg flex items-center shadow-lg hover:from-fifth hover:to-quaternary transition-all duration-300 ease-in-out transform hover:scale-105">
          <Link href="/bebidaslist" className="flex items-center space-x-2">
            <span>Alternar para Bebidas</span>
            <Image
              src="/images/Bebidas-Emoji.png"
              alt="Bebidas"
              width={25}
              height={25}
            />
          </Link>
        </button>
      </div>
    </section>
  );
};
