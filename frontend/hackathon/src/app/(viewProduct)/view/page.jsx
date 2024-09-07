// /app/viewProduct/page.jsx
import React from 'react';
import ProductCard from './components/ProductCard';
import styles from './page.module.css'
// aqui seria os dados puxados
const product = {
  id: 1,
  name: 'Hambúrguer Clássico',
  description: 'Um hambúrguer delicioso com queijo, alface e tomate.',
  price: 'R$ 25,00',
  desconto: 'R$ 32,00',
  imageUrl: '/images/Hamburguer.png'
};

export default function ViewProduct() {
  return (
    <div className={styles.page}>
      <ProductCard product={product} />
    </div>
  );
}


