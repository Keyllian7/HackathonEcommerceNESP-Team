import { toast } from "react-toastify";

export const adicionarAosFavoritos = (Item, favoritos, setFavoritos) => {
    
    if (!favoritos.some(fav => fav.id === Item.id)) {
        const novosFavoritos = [...favoritos, Item];
        localStorage.setItem('favoritos', JSON.stringify(novosFavoritos));
        setFavoritos(novosFavoritos);
        toast.success(`${Item.nome} foi adicionado aos favoritos!`);
    } else {
        toast.info(`${Item.nome} já está nos favoritos!`);
    }
};