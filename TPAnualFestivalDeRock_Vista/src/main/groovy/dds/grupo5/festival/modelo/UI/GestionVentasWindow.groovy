package dds.grupo5.festival.modelo.UI

import java.text.SimpleDateFormat
import java.util.Arrays
import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Control
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.SearchByExample
import org.uqbar.lacar.ui.model.Action
import dds.grupo5.festival.modelo.UI.VentaModel
import org.uqbar.arena.widgets.Button
import dds.grupo5.festival.modelo.UI.CrearEntradaWindow


class GestionVentasWindow extends MainWindow<VentaModel>{

	GestionVentasWindow() {
		super(new VentaModel())
		title = "Sistema de Venta de entradas"
	}

	@Override
	protected Panel createMainPanel(Panel mainPanel) {
		Panel myPanel = new Panel(mainPanel)
		myPanel.setLayout(new ColumnLayout(2))
	}

	@Override
	void createContents(Panel mainPanel) {
		mainPanel = this.createMainPanel(mainPanel)
		mainPanel.with {
			new Label(it).with { 
				text = "Nombre cliente:" 
			}
			
			new TextBox(it).with { 
				bindValueToProperty("nombreCliente")
			}
			
			new Label(it).with { 
				text = "Apellido cliente:" 
			}
			
			new TextBox(it).with { 
				bindValueToProperty("apellidoCliente") 
			}
			
			new Label(it).with { 
				text = "Fecha:" 
			}
			
			new TextBox(it).with { 
				bindValueToProperty("fechaVenta") 
			}
			
			def button = new Button(it).with {
				caption = "Seleccionar Entradas"
				onClick({ this.seleccionarEntradas()} as Action)
			}
		}
	}

	static void main(String[] args) {
		new GestionVentasWindow().startApplication()
	}
	
	public void seleccionarEntradas() {
		this.openDialog(new CrearEntradaWindow(this))
	}
}

