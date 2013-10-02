package dds.grupo5.festival.modelo.UI

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog
import dds.grupo5.festival.modelo.negocio.Entrada
import dds.grupo5.festival.modelo.festival.Festival
import dds.grupo5.festival.homes.HomeFestivales
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.windows.WindowOwner

class CrearEntradaWindow extends Dialog<EntradaModel> {

	public CrearEntradaWindow(WindowOwner parent) {
		super(parent, new EntradaModel())
		title = "Seleccione la entrada a comprar"
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel)
		form.with {
			layout = new ColumnLayout(2)
		/*	new Label(it).text = "Número"
			new TextBox(it).bindValueToProperty("numero")
			new Label(it).text = "Nombre del cliente"
			new TextBox(it).bindValueToProperty("nombre")
			new Label(it).text = "Modelo del aparato"
			*/
			new Selector<Festival>(it).with {
				allowNull(false)
			    bindValueToProperty("festival")
				bindItems(new ObservableProperty(HomeFestivales, "modelos"))
			}
		}
		
	}

}
