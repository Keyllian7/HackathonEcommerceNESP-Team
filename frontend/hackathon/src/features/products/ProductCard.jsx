export const ProductCard = ({ product }) => {
    return (
        <div className="bg-white">
            <h2 className="text-lg font-bold">{product.title}</h2>
            <p>{product.description}</p>
        </div>
    );
}