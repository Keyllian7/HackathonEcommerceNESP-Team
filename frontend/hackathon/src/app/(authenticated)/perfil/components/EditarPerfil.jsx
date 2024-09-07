import { useState } from "react";
import { useRouter } from "next/navigation";
import { usuario } from "../usuario";

export const EditarPerfil = () => {
  const [usuario, setUsuario] = useState({
    nome: "Usuário da Silva",
    email: "usuario.silva@example.com",
    endereco: "Rua das Flores, 123",
    cep: "12345-678",
    complemento: "Apto 101",
    referencia: "Próximo ao supermercado",
  });

  const [foto, setFoto] = useState(null);
  const [fotoPreview, setFotoPreview] = useState("/images/account-circle.png");
  const router = useRouter();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUsuario((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handlePhotoChange = async (e) => {
    const file = e.target.files[0];
    if (file) {
      if (!file.type.startsWith("image/")) {
        alert("Por favor, selecione um arquivo de imagem.");
        return;
      }

      const img = new Image();
      const reader = new FileReader();

      reader.onload = (event) => {
        img.src = event.target.result;
      };

      img.onload = () => {
        const canvas = document.createElement("canvas");
        const ctx = canvas.getContext("2d");

        const targetWidth = 300;
        const targetHeight = 400;
        canvas.width = targetWidth;
        canvas.height = targetHeight;

        const scale = Math.min(
          targetWidth / img.width,
          targetHeight / img.height
        );
        const width = img.width * scale;
        const height = img.height * scale;
        const offsetX = (targetWidth - width) / 2;
        const offsetY = (targetHeight - height) / 2;

        ctx.drawImage(img, offsetX, offsetY, width, height);

        const resizedImageURL = canvas.toDataURL("image/jpeg");
        setFoto(resizedImageURL);
        setFotoPreview(resizedImageURL);
      };

      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    console.log("Informações do usuário salvas:", usuario);
    console.log("Foto selecionada:", foto);

    router.push("/perfil");
  };

  return (
    <section className="mt-8 flex flex-col items-center max-w-md mx-auto p-8 bg-white rounded-xl shadow-lg border border-tertiary">
      <h1 className="text-3xl font-semibold mb-6 text-primary">
        Editar Perfil
      </h1>

      <form onSubmit={handleSubmit} className="w-full space-y-4">
        <div className="flex items-start space-x-4 mb-6">
          <div className="flex-shrink-0">
            <img
              src={fotoPreview}
              alt="Foto de Perfil"
              className="w-32 h-32 object-cover rounded-full border border-tertiary"
            />
          </div>
          <div className="flex-1">
            <label className="block text-secondary font-medium mb-1">
              Foto de Perfil:
            </label>
            <div className="relative flex items-center justify-center w-full h-32 bg-gray-100 border border-tertiary rounded-md cursor-pointer overflow-hidden">
              <input
                type="file"
                accept="image/*"
                onChange={handlePhotoChange}
                className="absolute inset-0 w-full h-full opacity-0 cursor-pointer"
              />
              <div className="text-center text-gray-500">
                <span className="block text-sm">
                  Clique ou arraste a imagem aqui
                </span>
                <span className="block text-xs">Formato 3x4</span>
              </div>
            </div>
          </div>
        </div>

        <div>
          <label className="block text-secondary font-medium mb-1">Nome:</label>
          <input
            type="text"
            name="nome"
            value={usuario.nome}
            onChange={handleChange}
            className="w-full p-2 border border-tertiary rounded-md focus:outline-none focus:ring-2 focus:ring-primary"
          />
        </div>

        <div>
          <label className="block text-secondary font-medium mb-1">
            Email:
          </label>
          <input
            type="email"
            name="email"
            value={usuario.email}
            onChange={handleChange}
            className="w-full p-2 border border-tertiary rounded-md focus:outline-none focus:ring-2 focus:ring-primary"
          />
        </div>

        <div>
          <label className="block text-secondary font-medium mb-1">
            Endereço:
          </label>
          <input
            type="text"
            name="endereco"
            value={usuario.endereco}
            onChange={handleChange}
            className="w-full p-2 border border-tertiary rounded-md focus:outline-none focus:ring-2 focus:ring-primary"
          />
        </div>

        <div>
          <label className="block text-secondary font-medium mb-1">Cep:</label>
          <input
            type="text"
            name="cep"
            value={usuario.cep}
            onChange={handleChange}
            className="w-full p-2 border border-tertiary rounded-md focus:outline-none focus:ring-2 focus:ring-primary"
          />
        </div>

        <div>
          <label className="block text-secondary font-medium mb-1">
            Complemento:
          </label>
          <input
            type="text"
            name="complemento"
            value={usuario.complemento}
            onChange={handleChange}
            className="w-full p-2 border border-tertiary rounded-md focus:outline-none focus:ring-2 focus:ring-primary"
          />
        </div>

        <div>
          <label className="block text-secondary font-medium mb-1">
            Referência:
          </label>
          <input
            type="text"
            name="referencia"
            value={usuario.referencia}
            onChange={handleChange}
            className="w-full p-2 border border-tertiary rounded-md focus:outline-none focus:ring-2 focus:ring-primary"
          />
        </div>

        <button
          type="submit"
          className="mt-6 w-full py-3 bg-primary text-white font-medium rounded-full hover:bg-secondary transition-all duration-300 transform hover:scale-105 shadow-md"
        >
          Salvar Alterações
        </button>
      </form>
    </section>
  );
};
