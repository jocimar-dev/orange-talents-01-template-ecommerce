package com.zup.nossocartao.acquisition;

/**
 * Todo evento relacioando ao sucesso de uma nova compra deve implementar 
 * essa interface
 * @author albertoluizsouza
 *
 */
public interface PurchaseSuccess {

	void processesPurchase(Purchase purchase);

}
