@APTBusqueda
Feature: Administracion de piezas por tarjeta
  Se prueba la busqueda por Sucursal, Nombre y Documento

  @BusquedaPorSucursal
  Scenario: Se realiza la busqueda por susucrsal
    Given Accedo a Admin de piezas con tarjeta
    When Accedo a consultas al Maestro CardCarrier
    And Accedo a Seleccion por NombreDocSucursal
    And Ingreso una sucursal
    And Presiono Ejecutar
    Then El sistema me muestra los resultados de la busqueda
    

	@BusquedaPorNombre
	 Scenario: Se realiza la busqueda por nombre
    Given Accedo a Admin de piezas con tarjeta
    When Accedo a consultas al Maestro CardCarrier
    And Accedo a Seleccion por NombreDocSucursal
    And Ingreso un nombre
    And Presiono Ejecutar
    Then El sistema me muestra los resultados de la busqueda
	
	
	@BusquedaPorDoc
	 Scenario: Se realiza la busqueda por cocumento
    Given Accedo a Admin de piezas con tarjeta
    When Accedo a consultas al Maestro CardCarrier
    And Accedo a Seleccion por NombreDocSucursal
    And Ingreso un documento
    And Presiono Ejecutar
    Then El sistema me muestra los resultados de la busqueda