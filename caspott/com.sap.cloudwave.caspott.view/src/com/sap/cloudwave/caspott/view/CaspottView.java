package com.sap.cloudwave.caspott.view;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sap.cloudwave.caspott.DataObjectsHandler.CodeArtifact;
import com.sap.cloudwave.caspott.DataObjectsHandler.ModelProvider;
import com.sap.cloudwave.caspott.DataObjectsHandler.RawLogData;
import com.sap.cloudwave.caspott.DataObjectsHandler.TableSorter;

public class CaspottView extends ViewPart {

	private TableViewer viewer;
	private TableSorter comparator;

	@PostConstruct
	public void createPartControl(Composite parent) {
		createViewer(parent);
		// Set the sorter for the table
		comparator = new TableSorter();
		viewer.setComparator(comparator);

	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// get the content for the viewer, setInput will call getElements in the
		// contentProvider
		List<CodeArtifact> model = populateCodeArtifacts();
		viewer.setInput(model);

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				System.out.println(event.getSelection().toString());

				event.getSource();

				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				Object selectedElement = selection.getFirstElement();
				CodeArtifact codeArtifact = (CodeArtifact) selectedElement;

				// do something with it
				System.out.println("Selection Listener working");
				openFileInEditor(codeArtifact);
			}
		});

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				System.out.println(event.getSelection().toString());

				event.getSource();

				IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
				Object firstElement = selection.getFirstElement();
				// do something with it
				System.out.println("Double Click Listener working");

			}
		});
	}

	// create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Project Name", "Source Code Artifact", "Procedure Name", "Response Time",
				"Number of Calls", "Number of Errors" };
		int[] bounds = { 100, 100, 100, 100, 100, 100 };

		// first column is for the Project name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				// Person p = (Person) element;
				// return p.getFirstName();
				CodeArtifact ca = (CodeArtifact) element;
				return ca.getProjectName();
			}
		});

		// second column is for the Code Artifact Name
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CodeArtifact ca = (CodeArtifact) element;
				return ca.getartifactName();
			}
		});

		// third column is for the procedure name
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CodeArtifact ca = (CodeArtifact) element;
				return ca.getProcedureName().toString();
			}
		});

		// fourth column is for the Response Time
		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CodeArtifact ca = (CodeArtifact) element;
				return ca.getresponseTime().toString();
			}
		});

		// fifth - No. of Calls
		col = createTableViewerColumn(titles[4], bounds[4], 4);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CodeArtifact ca = (CodeArtifact) element;
				return ca.getnoOfCalls().toString();
			}
		});

		// sixth - No. of Errors
		col = createTableViewerColumn(titles[5], bounds[5], 5);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				CodeArtifact ca = (CodeArtifact) element;
				return ca.getnoOfErrors().toString();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void openFileInEditor(CodeArtifact codeArtifact) {

		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage page = window.getActivePage();

			String path = "/" + codeArtifact.getProjectName() + "/" + codeArtifact.getartifactName().replace(".", "/")
					+ ".java";
			IPath ipath = new Path(path);

			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(ipath);

			HashMap map = new HashMap();
			map.put(IMarker.LINE_NUMBER, new Integer(codeArtifact.getLineNo()));
			// map.put(IMarker.LINE_NUMBER,
			// new Integer(getMethodLineNumber(codeArtifact.getProcedureName(),
			// codeArtifact.getartifactName())));
			map.put(IWorkbenchPage.EDITOR_ID_ATTR, "org.eclipse.ui.DefaultEditor");
			IMarker marker = file.createMarker(IMarker.TEXT);
			marker.setAttributes(map);
			IDE.openEditor(page, marker, true);
			marker.delete();

		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public List<CodeArtifact> populateCodeArtifacts() {
		RawLogData rawLogData;
		// retrieve path from environmental variables
		Map<String, String> envVars = new HashMap<String, String>();
		envVars = System.getenv();
		String jsonFilePath = envVars.get("CASPOTT");
		String JSON;
		try {
			File file = new File(jsonFilePath);
			String jsonFileAbsolutePath = file.getAbsolutePath();
			JSON = readFile(jsonFileAbsolutePath, StandardCharsets.UTF_8);
			System.out.println(JSON);

			GsonBuilder profileJSON = new GsonBuilder();
			profileJSON.setPrettyPrinting().serializeNulls();
			Gson gson = profileJSON.create();
			rawLogData = gson.fromJson(JSON, RawLogData.class);

			System.out.println("Printing the Object");
			System.out.println(rawLogData);
			ModelProvider.addCodeArtifacts(rawLogData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ModelProvider.getCodeArtifacts();
	}

	/*
	 * public int getMethodLineNumber(String methodName, String pkg) { try {
	 * System.out.println("Get method line number with javassist\n"); ClassPool
	 * pool = ClassPool.getDefault(); CtClass cc = pool.get(pkg); CtMethod
	 * methodX = cc.getDeclaredMethod(methodName);
	 * 
	 * int xlineNumber = methodX.getMethodInfo().getLineNumber(0);
	 * System.out.println("method x is on line " + xlineNumber + "\n"); return
	 * xlineNumber; } catch (NotFoundException e) { // TODO Auto-generated
	 * catchblock e.printStackTrace(); return 0; } }
	 */

}