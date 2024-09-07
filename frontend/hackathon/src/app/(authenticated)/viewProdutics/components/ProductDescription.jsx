import React from "react";

export const ProductDescription = ({ item }) => {
  if (!item) {
    return <div>Item não encontrado</div>;
  }

  return (
    <div className="flex justify-center items-center min-h-screen bg-primary">
      {" "}
      <div className="bg-white p-8 rounded-lg shadow-lg max-w-sm w-full">
        {" "}
        <h1 className="text-3xl font-bold mb-6 text-center">{item.nome}</h1>
        <p className="text-gray-600 text-center">{item.descrição}</p>
        <div className="flex justify-center mb-6">
          <img
            src={item.imagem}
            alt={item.nome}
            className="w-full h-24 object-contain mb-2 rounded-lg"
          />{" "}
        </div>
        <p className="text-xl font-semibold text-gray-800 text-center mb-4">
          Preço: {item.preço}
        </p>
        <button className="bg-primary text-white font-semibold py-2 px-4 rounded-md w-full border border-transparent hover:bg-secondary transition-colors duration-300 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-opacity-50">
          Adicionar ao Carrinho
        </button>
      </div>
    </div>
  );
};
