// /components/ProductCard.jsx
import React from 'react';
import CartButton from './CartButton';
import styles from './Product.module.css'
import { GiReturnArrow } from "react-icons/gi";
export default function ProductCard({ product }) {
  const { name, description, price, desconto, imageUrl } = product;

  return (
    <div className={styles.card}>
      <div className={styles.return}><a href="/home"><GiReturnArrow /></a></div>
      <img src={imageUrl} alt={name} />
      <div className={styles.details}>
        <h2>{name}</h2>
        <p className={styles.description}>{description}</p>
        <div className={styles.price}>
          <p>{price}</p>
          <p className={styles.desc}>{desconto}</p>
        </div>
        <div style={{ marginTop: '20px' }}>
        <label htmlFor="note" style={{ display: 'block', marginBottom: '8px' }}>Observações:</label>
        <textarea className={styles.textarea}
          id="note"
          rows="4"
          placeholder="Digite aqui suas observações (ex: tirar cebola , tirar tomate...)"
        />
      </div>
        <div>
          <CartButton product={product} />
        </div>
      </div>
    </div>
  );
}


