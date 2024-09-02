
export const generateMetadata = () => {
  return {
    title: "Register",
    description: "Registre-se no Burguer Service",
  };
};

export default function Register() {
  return (
    <form className="flex flex-col gap-4 bg-white sm:bg-none rounded-xl p-6 md:p-14">
      <div className="flex flex-col gap-2 w-96">
        <label htmlFor="email">Email</label>
        <input
          type="email"
          id="email"
          className="w-full border border-gray-400 rounded-md opacity-80 h-10 px-4"
          placeholder="Email"
        />
      </div>
      <div className="flex flex-col gap-2 w-96">
        <label>Senha</label>
        <input
          type="password"
          id="password"
          className="w-full border border-gray-400 rounded-md opacity-80 h-10 px-4"
          placeholder="Senha"
        />
      </div>
      <div className="flex flex-col gap-2 w-96">
        <label>Confirmar senha</label>
        <input
          type="password"
          id="confirmPassword"
          className="w-full border border-gray-400 rounded-md opacity-80 h-10 px-4"
          placeholder="Confirmar Senha"
        />
      </div>
      <div className="flex gap-3 items-center">
        <input
          type="checkbox"
          id="checkbox"
          placeholder="Confirmar Senha"
        />
        <label>Concorda com os termos?</label>
      </div>

      <div className="flex flex-col gap-2 w-96">
        <p>Já tem uma conta? <a className="text-cyan-800" href="/login">Clique aqui</a></p>
        <button type="submit" className="bg-black text-white py-2 px-4 w-full rounded-lg">Cadastrar</button>
      </div>
    </form>
  )
}