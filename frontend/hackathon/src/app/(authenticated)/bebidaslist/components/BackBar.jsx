"use client"

import Image from "next/image"
import Link from "next/link"

export const BackBar = () => {
  const handleBackClick = () => {
    window.history.back();
  }
  
  return (
    <header className="flex justify-between items-center mb-2 p-2">
      <div className="flex items-center gap-2">
        <Image src="/images/account-circle.png" alt="Perfil" width={42} height={42} />
        <button onClick={handleBackClick}>
          <Image src="/images/Arrow left-circle.png" alt="Botao para Voltar" width={42} height={42}/>
        </button>
      </div>
      <Link href="/">
        <Image src="/images/logo.png" width={52} height={42} alt="Home Button" className="h-full object-cover" />
      </Link>
      <div className="flex space-x-4">
        <Image className="object-contain w-8" src="/images/notifications.png" alt="Notificação" width={32} height={32} />
        <Image className="object-contain" src="/images/menu.png" alt="Menu" width={32} height={32} />
      </div>
    </header>
  )
}