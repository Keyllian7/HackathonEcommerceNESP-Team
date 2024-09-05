export const PriceSummary = ({ price, freight }) => {

    const total = price + freight

    return (
        <div className="flex flex-col gap-2 bg-white rounded-xl p-2">
            <span>Subtotal: R$ {price.toFixed(2)}</span>
            <span>Frete: R$ {freight.toFixed(2)}</span>
            <span>Total: R$ {total.toFixed(2)}</span>
        </div>
    )
}