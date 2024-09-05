import { toast } from "react-toastify";

export const removerDosFavoritos = (id, favoritos, setFavoritos) => {
    
    const novosFavoritos = favoritos.filter((Item) => Item.id !== id);
    setFavoritos(novosFavoritos);
    localStorage.setItem('favoritos', JSON.stringify(novosFavoritos));
    toast.error('Item removido dos favoritos!');
};