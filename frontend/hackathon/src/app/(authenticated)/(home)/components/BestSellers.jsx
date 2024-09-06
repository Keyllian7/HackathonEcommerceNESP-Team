import Image from "next/image";
import { useState, useEffect } from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Items } from "../../(Items)/components/Items";
import { adicionarAosFavoritos } from "../../utils/adicionarFavorites";
import Link from "next/link";

export const BestSellers = ({ categoriaSelecionada }) => {
  const [favoritos, setFavoritos] = useState([]);
  const selecionarItems = Items[categoriaSelecionada];

  useEffect(() => {
    const favoritosSalvos = JSON.parse(localStorage.getItem("favoritos")) || [];
    setFavoritos(favoritosSalvos);
  }, []);

  const melhoresItens = selecionarItems.slice(0, 2);

  const caminhoMap = {
    burguer: "/burguerlist",
    bebidas: "/bebidaslist",
  };
  const caminho = caminhoMap[categoriaSelecionada] || "/";

  return (
    <section>
      <ToastContainer />
      <div className="flex justify-between font-semibold mb-2 p-2">
        <h2>Mais Vendidos</h2>
        <a
          href={caminho}
          className="text-sm font-light text-black hover:underline"
        >
          Ver Todos
        </a>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        {melhoresItens.map((Item) => (
          <div
            key={Item.id}
            className="bg-white p-4 rounded-lg shadow-lg flex flex-col"
          >
            <Image
              src={Item.imagem}
              alt={Item.nome}
              className="w-full h-24 object-contain mb-2 rounded-lg"
              width={200}
              height={200}
            />
            <h3 className="font-semibold text-lg">{Item.nome}</h3>
            <p className="font-bold text-lg mt-1">{Item.preço}</p>
            <div className="flex flex-col sm:flex-row items-center justify-between mt-4">
              <Link href={`/viewProdutics?id=${Item.id}`}>
                <button className="bg-primary text-white px-4 py-2 rounded-md border border-transparent hover:bg-secondary transition-colors duration-300 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-opacity-50 text-sm sm:text-base">
                  Ver Detalhes
                </button>
              </Link>
              <Image
                className="w-6 h-6 cursor-pointer mt-2 sm:mt-0"
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
        ))}
      </div>
    </section>
  );
};
