import { BestSellingProducts } from "@/features/products/BestSellingProducts";
import { ProductTypeSelector } from "@/features/products/ProductTypeSelector";

const getProduct = async () => {
  return {
    id: 1,
    title: "batata",
    price: 1.99,
    description: "um limao",
  }
}

export const generateMetadata = async () => {
  const product = await getProduct()

  return {
    title: product.title,
    description: product.description,
  };
}

export default function Home({searchParams}) {
  return (
    <main>
      <BestSellingProducts filter={searchParams.filter}/>
      <ProductTypeSelector />
    </main>
  );
}
