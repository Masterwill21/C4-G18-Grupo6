import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { ValidarTokenGuard } from './guards/validar-token.guard';


import { CrearEmpleadoComponent } from './principal/crear-empleado/crear-empleado.component';
import { CrearNominaComponent } from './principal/crear-nomina/crear-nomina.component';
import { InicioComponent } from './principal/inicio/inicio.component';
import { ListarEmpleadosComponent } from './principal/listar-empleados/listar-empleados.component';
import { EntrarComponent } from './usuario/entrar/entrar.component';
import { RegistrarComponent } from './usuario/registrar/registrar.component';

const routes: Routes = [
  {
    path:'',
    component: EntrarComponent
  },
  {
    path:'inicio',
    component: InicioComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'empleados',
    component: ListarEmpleadosComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'new-empleado',
    component: CrearEmpleadoComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'new-nomina',
    component: CrearNominaComponent,
    canActivate: [ValidarTokenGuard],
    canLoad:[ValidarTokenGuard]
  },
  {
    path:'registrar',
    component: RegistrarComponent
  },
  {
    path:'**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
