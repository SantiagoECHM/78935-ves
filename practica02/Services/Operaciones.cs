using System;
using WSDL.mensajes;

namespace WSDL.operaciones
{
    public class Operaciones : Mensajes
    {
        private List<string> name = new List<string>();
        public string Saludar(string nombre)
        {
            name.Add(nombre);
            string msj = "Hola " + nombre;
            return msj;
        }
        public string Mostrar(int id)
        {
            return name[id];
        }
    }
}

