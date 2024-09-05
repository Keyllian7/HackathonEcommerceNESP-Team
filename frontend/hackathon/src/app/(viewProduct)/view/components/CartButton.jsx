// /components/CartButton.jsx
'use client';

import React, { useEffect, useState } from 'react';
import style from './cardbutton.module.css'
export default function CartButton({ product }) {
  const [quantity, setQuantity] = useState(1);
  const [NewPrice, setNewPrice] = useState(0);
  const { name, description, price, desconto} = product;


  useEffect(() => {
    if (product) {
      // Converta o preço para número e atualize o novo preço
      const basePrice = parseFloat(product.price.replace('R$', '').replace(',', '.'));
      const updatedPrice = basePrice * quantity;
      setNewPrice(formatPrice(updatedPrice.toFixed(2))); // Formata o preço com vírgula
    }
  }, [quantity, product]);

  const formatPrice = (price) => {
    return price.replace('.', ',');
  };

  const handleIncrease = () => {
    setQuantity(prevQuantidade => prevQuantidade + 1);
  }

  const handleDecrease = () => {
    setQuantity(prevQuantidade => ( prevQuantidade > 1 ? prevQuantidade - 1 : 1))
  };

  const handleAddToCart = () => {
    if (!product) {
      alert('Produto não disponível.');
      return;
    }

   
    alert(`Produto adicionado ao carrinho!
      Nome: ${name}
      Descrição: ${description}
      Preço: ${price}
      Desconto: ${desconto ? desconto : 'Nenhum desconto'}
      Quantidade: ${quantity}`);
  };

  return (
    <div className={styles.container}>
      <button onClick={handleDecrease} style={styles.button}>-</button>
      <span style={styles.quantity}>{quantity}</span>
      <button onClick={handleIncrease} style={styles.button}>+</button>
      <button onClick={handleAddToCart} style={styles.addButton}>
        Adicionar ao Carrinho R${NewPrice}
      </button>
    </div>
  );
}

const styles = {
  
  button: {
   
    color: '#555',
    border: 'none',
    padding: '10px',
    fontSize: '18px',
    cursor: 'pointer',
    borderRadius: '4px',
    margin: '0 5px',
  },
  quantity: {
    fontSize: '18px',
    margin: '0 10px',
    color: '#963C08',
  },
  addButton: {
    backgroundColor: '#963C08',
    color: '#fff',
    border: 'none',
    padding: '10px 20px',
    fontSize: '16px',
    cursor: 'pointer',
    borderRadius: '4px',
  },
};
