using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class CellsService : BaseService
    {
        public CellsService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;

            WorksheetColumns = new CellsWorksheetColumn(AppSid, AppKey);
            Autoshapes = new CellsAutoshape(AppSid, AppKey);
            BarCodes = new CellsBarCode(AppSid, AppKey);
            ChartArea = new CellsChartArea(AppSid, AppKey);
            Charts = new CellsChart(AppSid, AppKey);
            Hypelinks = new CellsHypelink(AppSid, AppKey);
            OleObjects = new CellsOleObject(AppSid, AppKey);
            Pictures = new CellsPicture(AppSid, AppKey);
            Properties = new CellsProperties(AppSid, AppKey);
            SaveAs = new CellsSaveAs(AppSid, AppKey);
            Workbook = new CellsWorkbook(AppSid, AppKey);
            Worksheets = new CellsWorksheet(AppSid, AppKey);
            WorksheetValidations = new CellsWorksheetValidation(AppSid, AppKey);
        }

        public CellsWorksheetColumn WorksheetColumns { get; set; }
        public CellsAutoshape Autoshapes { get; set; }
        public CellsBarCode BarCodes { get; set; }
        public CellsChartArea ChartArea { get; set; }
        public CellsChart Charts { get; set; }
        public CellsHypelink Hypelinks { get; set; }
        public CellsOleObject OleObjects { get; set; }
        public CellsPicture Pictures { get; set; }
        public CellsProperties Properties { get; set; }
        public CellsSaveAs SaveAs { get; set; }
        public CellsWorkbook Workbook { get; set; }
        public CellsWorksheet Worksheets { get; set; }
        public CellsWorksheetValidation WorksheetValidations { get; set; }

        public class CellsWorksheetColumn
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsWorksheetColumn(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read worksheet columns info.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorksheetColumnsResponse ReadWorksheetColumnsInfo(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells/columns?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorksheetColumnsResponse WorksheetColumnsResponse = jObject.ToObject<WorksheetColumnsResponse>();
                return WorksheetColumnsResponse;
            }

            /// <summary>
            /// Set worksheet column width.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="columnIndex">The column index.</param>
            /// <param name="width">The width.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorksheetColumnResponse SetWorksheetColumnWidth(string name, string sheetName, int columnIndex, int width, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/columns/{columnIndex}?width={width}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/{2}?width={3}&storage={4}&folder={5}",
                                                name, sheetName, columnIndex, width, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WorksheetColumnResponse WorksheetColumnResponse = jObject.ToObject<WorksheetColumnResponse>();
                return WorksheetColumnResponse;

            }

            /// <summary>
            /// Read worksheet column data by column's index.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="columnIndex">The column index.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorksheetColumnResponse ReadWorksheetColumnDataByColumnIndex(string name, string sheetName, int columnIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells/columns/{columnIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/{2}?storage={3}&folder={4}",
                                                name, sheetName, columnIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorksheetColumnResponse WorksheetColumnResponse = jObject.ToObject<WorksheetColumnResponse>();
                return WorksheetColumnResponse;
            }

            /// <summary>
            /// Insert worksheet columns.
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="columnIndex">The column index.</param>
            /// <param name="columns">The columns.</param>
            /// <param name="updateReference">The update reference.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorksheetColumnsResponse InsertWorksheetColumns(string name, string sheetName, int columnIndex, int columns, bool updateReference, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/cells/columns/{columnIndex}?columns={columns}&appSid={appSid}&updateReference={updateReference}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/{2}?columns={3}&updateReference={4}&storage={5}&folder={6}",
                                                name, sheetName, columnIndex, columns, updateReference, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
                WorksheetColumnsResponse WorksheetColumnsResponse = jObject.ToObject<WorksheetColumnsResponse>();
                return WorksheetColumnsResponse;
            }

            /// <summary>
            /// Delete worksheet columns.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="columnIndex">The column index.</param>
            /// <param name="columns">The columns.</param>
            /// <param name="updateReference">The update reference.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void DeleteWorksheetColumns(string name, string sheetName, int columnIndex, int columns, bool updateReference, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/cells/columns/{columnIndex}?columns={columns}&updateReference={updateReference}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/{2}?columns={3}&updateReference={4}&storage={5}&folder={6}",
                                                name, sheetName, columnIndex, columns, updateReference, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Hide worksheet columns.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startColumn">The begin column index to be operated.</param>
            /// <param name="totalColumns">Number of columns to be operated.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void HideWorksheetColumns(string name, string sheetName, int startColumn, int totalColumns, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/columns/hide?startColumn={startColumn}&totalColumns={totalColumns}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/hide?startColumn={2}&totalColumns={3}&storage={4}&folder={5}",
                                                name, sheetName, startColumn, totalColumns, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Unhide worksheet columns.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startcolumn">The begin column index to be operated.</param>
            /// <param name="totalColumns">Number of columns to be operated.</param>
            /// <param name="width">The new column width.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UnhideWorksheetColumns(string name, string sheetName, int startcolumn, int totalColumns, int width, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/columns/unhide?startcolumn={startcolumn}&totalColumns={totalColumns}&appSid={appSid}&width={width}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/unhide?startcolumn={2}&totalColumns={3}&width={4}&storage={5}&folder={6}",
                                                name, sheetName, startcolumn, totalColumns, width, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Group worksheet columns.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="firstIndex">The first column index to be operated.</param>
            /// <param name="lastIndex">The last column index to be operated.</param>
            /// <param name="hide">columns visible state</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void GroupWorksheetColumns(string name, string sheetName, int firstIndex, int lastIndex, bool hide, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/columns/group?firstIndex={firstIndex}&lastIndex={lastIndex}&appSid={appSid}&hide={hide}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/group?firstIndex={2}&lastIndex={3}&hide={4}&storage={5}&folder={6}",
                                                name, sheetName, firstIndex, lastIndex, hide, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Ungroup worksheet columns.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="firstIndex">The first column index to be operated.</param>
            /// <param name="lastIndex">The last column index to be operated.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UngroupWorksheetColumns(string name, string sheetName, int firstIndex, int lastIndex, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/columns/ungroup?firstIndex={firstIndex}&lastIndex={lastIndex}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/ungroup?firstIndex={2}&lastIndex={3}&storage={4}&folder={5}",
                                                name, sheetName, firstIndex, lastIndex, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Copy worksheet columns.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="sourceColumnIndex">Source column index</param>
            /// <param name="destinationColumnIndex">Destination column index</param>
            /// <param name="columnNumber">The copied column number</param>
            /// <param name="worksheet">The Worksheet</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void CopyWorksheetColumns(string name, string sheetName, int sourceColumnIndex, int destinationColumnIndex, int columnNumber, string worksheet, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/columns/copy?sourceColumnIndex={sourceColumnIndex}&destinationColumnIndex={destinationColumnIndex}&columnNumber={columnNumber}&appSid={appSid}&worksheet={worksheet}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/columns/copy?sourceColumnIndex={2}&destinationColumnIndex={3}&columnNumber={4}&worksheet={5}&storage={6}&folder={7}",
                                                name, sheetName, sourceColumnIndex, destinationColumnIndex, columnNumber, worksheet, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Clear cells contents.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="range">The range.</param>
            /// <param name="startRow">The start row.</param>
            /// <param name="startColumn">The start column.</param>
            /// <param name="endRow">The end row.</param>
            /// <param name="endColumn">The end column.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void ClearCellsContents(string name, string sheetName, string range, int startRow, int startColumn, int endRow, int endColumn, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/clearcontents?appSid={appSid}&range={range}&startRow={startRow}&startColumn={startColumn}&endRow={endRow}&endColumn={endColumn}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/clearcontents?range={2}&startRow={3}&startColumn={4}&endRow={5}&endColumn={6}&storage={7}&folder={8}",
                                                name, sheetName, range, startRow, startColumn, endRow, endColumn, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Clear cells formats.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="range">The range.</param>
            /// <param name="startRow">The start row.</param>
            /// <param name="startColumn">The start column.</param>
            /// <param name="endRow">The end row.</param>
            /// <param name="endColumn">The end column.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void ClearCellsFormats(string name, string sheetName, string range, int startRow, int startColumn, int endRow, int endColumn, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/clearformats?appSid={appSid}&range={range}&startRow={startRow}&startColumn={startColumn}&endRow={endRow}&endColumn={endColumn}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/clearformats?range={2}&startRow={3}&startColumn={4}&endRow={5}&endColumn={6}&storage={7}&folder={8}",
                                                name, sheetName, range, startRow, startColumn, endRow, endColumn, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Update cell's range style.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="range">The range.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UpdateCellRangeStyle(string name, string sheetName, string range, WorkbookStyle style, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/style?range={range}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/style?range={2}&storage={3}&folder={4}",
                                                name, sheetName, range, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(style));
            }

            /// <summary>
            /// Merge cells.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startRow">The start row.</param>
            /// <param name="startColumn">The start column.</param>
            /// <param name="totalRows">The total rows</param>
            /// <param name="totalColumns">The total columns.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void MergeCells(string name, string sheetName, int startRow, int startColumn, int totalRows, int totalColumns, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/merge?startRow={startRow}&startColumn={startColumn}&totalRows={totalRows}&totalColumns={totalColumns}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/merge?startRow={2}&startColumn={3}&totalRows={4}&totalColumns={5}&storage={6}&folder={7}",
                                                name, sheetName, startRow, startColumn, totalRows, totalColumns, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Unmerge cells.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startRow">The start row.</param>
            /// <param name="startColumn">The start column.</param>
            /// <param name="totalRows">The total rows</param>
            /// <param name="totalColumns">The total columns.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UnmergeCells(string name, string sheetName, int startRow, int startColumn, int totalRows, int totalColumns, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/unmerge?startRow={startRow}&startColumn={startColumn}&totalRows={totalRows}&totalColumns={totalColumns}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/unmerge?startRow={2}&startColumn={3}&totalRows={4}&totalColumns={5}&storage={6}&folder={7}",
                                                name, sheetName, startRow, startColumn, totalRows, totalColumns, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get cells info.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="offest">Beginning offset.</param>
            /// <param name="count">Maximum amount of cells in the response.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public CellsResponse GetCellsInfo(string name, string sheetName, int offest, int count, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells?appSid={appSid}&offest={offest}&count={count}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells?offest={2}&count={3}&storage={4}&folder={5}",
                                                name, sheetName, offest, count, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsResponse CellsResponse = jObject.ToObject<CellsResponse>();
                return CellsResponse;
            }

            /// <summary>
            /// Set cell range value
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="cellarea">Cell area (like "A1:C2")</param>
            /// <param name="value">Range value</param>
            /// <param name="type">Value data type (like "int")</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void SetCellRangeValue(string name, string sheetName, string cellarea, string value, string type, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells?cellarea={cellarea}&value={value}&type={type}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells?cellarea={2}&value={3}&type={4}&storage={5}&folder={6}",
                                                name, sheetName, cellarea, value, type, storage, folder);

                ServiceController.Get(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Read cell data by cell's name.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="cellOrMethodName">The cell's or method name. (Method name like firstcell, endcell etc.)</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorkbookCellResponse ReadCellDataByCellName(string name, string sheetName, string cellOrMethodName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells/{cellOrMethodName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/{2}?storage={3}&folder={4}",
                                                name, sheetName, cellOrMethodName, storage, folder);


                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookCellResponse workbookCellResponse = jObject.ToObject<WorkbookCellResponse>();
                return workbookCellResponse;
            }

            /// <summary>
            /// Read cell's style info.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="cellName">Cell's name.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorkbookStyleResponse ReadCellStyleInfo(string name, string sheetName, string cellName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells/{cellName}/style?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/{2}/style?storage={3}&folder={4}",
                                                name, sheetName, cellName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookStyleResponse workbookStyleResponse = jObject.ToObject<WorkbookStyleResponse>();
                return workbookStyleResponse;
            }

            /// <summary>
            /// Update cell's style.
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="cellName">The cell name.</param>
            /// <param name="style">WorkbookStyle</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UpdateCellStyle(string name, string sheetName, string cellName, WorkbookStyle style, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/{cellName}/style?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/{2}/style?storage={3}&folder={4}",
                                                name, sheetName, cellName, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(style));
            }

            /// <summary>
            /// Set cell value.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="cellName">The cell name.</param>
            /// <param name="value">The cell value.</param>
            /// <param name="type">The value type.</param>
            /// <param name="formula">Formula for cell</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorkbookCellResponse SetCellValue(string name, string sheetName, string cellName, string value, string type, string formula, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/{cellName}?appSid={appSid}&value={value}&type={type}&formula={formula}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/{2}?value={3}&type={4}&formula={5}&storage={6}&folder={7}",
                                                name, sheetName, cellName, value, type, formula, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookCellResponse WorkbookCellResponse = jObject.ToObject<WorkbookCellResponse>();
                return WorkbookCellResponse;
            }

            /// <summary>
            /// Copy cell into cell	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="destCellName">Destination cell name</param>
            /// <param name="worksheet">Destination worksheet name.</param>
            /// <param name="cellname">Source cell name</param>
            /// <param name="row">Source row</param>
            /// <param name="column">Source column</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void CopyCellIntoCell(string name, string sheetName, string destCellName, string worksheet, string cellname, int row, int column, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/{destCellName}/copy?worksheet={worksheet}&appSid={appSid}&cellname={cellname}&row={row}&column={column}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/{2}/copy?worksheet={3}&cellname={4}&row={5}&column={6}&storage={7}&folder={8}",
                                                name, sheetName, destCellName, worksheet, cellname, row, column, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Set htmlstring value into cell	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="cellName">The cell name.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void SetHtmlstringValueIntoCell(string name, string sheetName, string cellName, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/{cellName}/htmlstring?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/{2}/htmlstring?storage={3}&folder={4}",
                                                name, sheetName, cellName, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Read worksheet rows info.
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorksheetRowsResponse ReadWorksheetRowsInfo(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells/rows?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorksheetRowsResponse WorksheetRowsResponse = jObject.ToObject<WorksheetRowsResponse>();
                return WorksheetRowsResponse;
            }

            /// <summary>
            /// Delete several worksheet rows.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startrow">The begin row index to be operated.</param>
            /// <param name="totalRows">Number of rows to be operated.</param>
            /// <param name="updateReference">Indicates if update references in other worksheets.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void DeleteSeveralWorksheetRows(string name, string sheetName, int startrow, int totalRows, bool updateReference, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/cells/rows?startrow={startrow}&appSid={appSid}&totalRows={totalRows}&updateReference={updateReference}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows?startrow={2}&totalRows={3}&updateReference={4}&storage={5}&folder={6}",
                                                name, sheetName, startrow, totalRows, updateReference, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Insert several new worksheet rows.
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startrow">The begin row index to be operated.</param>
            /// <param name="totalRows">Number of rows to be operated.</param>
            /// <param name="updateReference">Indicates if update references in other worksheets.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void InsertSeveralNewWorksheetRows(string name, string sheetName, int startrow, int totalRows, bool updateReference, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/cells/rows?startrow={startrow}&appSid={appSid}&totalRows={totalRows}&updateReference={updateReference}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows?startrow={2}&totalRows={3}&updateReference={4}&storage={5}&folder={6}",
                                                name, sheetName, startrow, totalRows, updateReference, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Read worksheet row data by row's index.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="rowIndex">The row index.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public WorksheetRowResponse ReadWorksheetRowDataByRowIndex(string name, string sheetName, int rowIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/cells/rows/{rowIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/{2}?storage={3}&folder={4}",
                                                name, sheetName, rowIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorksheetRowResponse WorksheetRowResponse = jObject.ToObject<WorksheetRowResponse>();
                return WorksheetRowResponse;
            }

            /// <summary>
            /// Delete worksheet row.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="rowIndex">The row index.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void DeleteWorksheetRow(string name, string sheetName, int rowIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/cells/rows/{rowIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/{2}?storage={3}&folder={4}",
                                                name, sheetName, rowIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Insert new worksheet row.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="rowIndex">The row index.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void InsertNewWorksheetRow(string name, string sheetName, int rowIndex, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/cells/rows/{rowIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/{2}?storage={3}&folder={4}",
                                                name, sheetName, rowIndex, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Update worksheet row.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="rowIndex">The row index.</param>
            /// <param name="height">The new row height.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UpdateWorksheetRow(string name, string sheetName, int rowIndex, int height, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/rows/{rowIndex}?appSid={appSid}&height={height}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/{2}?height={3}&storage={4}&folder={5}",
                                                name, sheetName, rowIndex, height, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Hide worksheet rows.
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startrow">The begin row index to be operated.</param>
            /// <param name="totalRows">Number of rows to be operated.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void HideWorksheetRows(string name, string sheetName, int startrow, int totalRows, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/rows/hide?startrow={startrow}&totalRows={totalRows}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/hide?startrow={2}&totalRows={3}&storage={4}&folder={5}",
                                                name, sheetName, startrow, totalRows, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Unhide worksheet rows.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="startrow">The begin row index to be operated.</param>
            /// <param name="totalRows">Number of rows to be operated.</param>
            /// <param name="height">The new row height.</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UnhideWorksheetRows(string name, string sheetName, int startrow, int totalRows, int height, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/rows/unhide?startrow={startrow}&totalRows={totalRows}&appSid={appSid}&height={height}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/unhide?startrow={2}&totalRows={3}&height={4}&storage={5}&folder={6}",
                                                name, sheetName, startrow, totalRows, height, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Group worksheet rows.
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="firstIndex">The first row index to be operated.</param>
            /// <param name="lastIndex">The last row index to be operated.</param>
            /// <param name="hide">rows visible state</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void GroupWorksheetRows(string name, string sheetName, int firstIndex, int lastIndex, bool hide, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/rows/group?firstIndex={firstIndex}&lastIndex={lastIndex}&appSid={appSid}&hide={hide}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/group?firstIndex={2}&lastIndex={3}&hide={4}&storage={5}&folder={6}",
                                                name, sheetName, firstIndex, lastIndex, hide, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Ungroup worksheet rows.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="firstIndex">The first row index to be operated.</param>
            /// <param name="lastIndex">The last row index to be operated.</param>
            /// <param name="isAll">Is all row to be operated</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void UngroupWorksheetRows(string name, string sheetName, int firstIndex, int lastIndex, bool isAll, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/rows/ungroup?firstIndex={firstIndex}&lastIndex={lastIndex}&appSid={appSid}&isAll={isAll}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/ungroup?firstIndex={2}&lastIndex={3}&isAll={4}&storage={5}&folder={6}",
                                                name, sheetName, firstIndex, lastIndex, isAll, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Copy worksheet rows.	
            /// </summary>
            /// <param name="name">The workbook name.</param>
            /// <param name="sheetName">The worksheet name.</param>
            /// <param name="sourceRowIndex">Source row index</param>
            /// <param name="destinationRowIndex">Destination row index</param>
            /// <param name="rowNumber">The copied row number</param>
            /// <param name="worksheet">worksheet</param>
            /// <param name="folder">The workbook folder.</param>
            /// <param name="storage">Workbook storage.</param>
            public void CopyWorksheetRows(string name, string sheetName, int sourceRowIndex, int destinationRowIndex, int rowNumber, string worksheet, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/cells/rows/copy?sourceRowIndex={sourceRowIndex}&destinationRowIndex={destinationRowIndex}&rowNumber={rowNumber}&appSid={appSid}&worksheet={worksheet}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/cells/rows/copy?sourceRowIndex={2}&destinationRowIndex={3}&rowNumber={4}&worksheet={5}&storage={6}&folder={7}",
                                                name, sheetName, sourceRowIndex, destinationRowIndex, rowNumber, worksheet, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }
        }

        public class CellsAutoshape
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsAutoshape(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Get worksheet autoshapes info. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>AutoShapesResponse object</returns>
            public AutoShapesResponse GetWorksheetAutoshapesInfo(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{0}/Worksheets/{1}/Autoshapes?storage={2}&folder={3} 

                string apiUrl = string.Format(@"cells/{0}/Worksheets/{1}/Autoshapes?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AutoShapesResponse autoShapesResponse = jObject.ToObject<AutoShapesResponse>();
                return autoShapesResponse;
            }

            /// <summary>
            /// Get autoshape info
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="autoShapeNumber">The autoshape number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>AutoShapeResponse object</returns>
            public AutoShapeResponse GetAutoshapeInfo(string name, string sheetName, int autoShapeNumber, string folder, string storage = "")
            {
                // GET 	cells/{name}/Worksheets/{sheetName}/Autoshapes/{AutoshapeNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/Worksheets/{1}/Autoshapes/{2}?storage={3}&folder={4}",
                                                name, sheetName, autoShapeNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AutoShapeResponse autoShapeResponse = jObject.ToObject<AutoShapeResponse>();
                return autoShapeResponse;
            }

            /// <summary>
            ///  Get the autoshape in some format. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="autoShapeNumber">The autoshape number.</param>
            /// <param name="format">Autoshape conversion format.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="outPath">The output file name with full path to save autoshape e.g. c:\out.jpeg</param>
            /// <param name="storage">The document storage. </param>
            public void GetAutoshapeInSomeFormat(string name, string sheetName, int autoShapeNumber, CellsAutoshapeFormat format, string folder, string outPath, string storage = "")
            {
                // GET 	cells/{name}/Worksheets/{sheetName}/Autoshapes/{AutoshapeNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/Worksheets/{1}/Autoshapes/{2}?format={3}&storage={4}&folder={5}",
                                                name, sheetName, autoShapeNumber, format, storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = System.IO.File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }
        }

        public class CellsBarCode
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsBarCode(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Extract barcodes from worksheet picture. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="pictureNumber">Picture index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>BarcodeResponse object</returns>
            public BarcodeResponse ExtractBarCodesFromWorksheetPicture(string name, string sheetName, int pictureNumber, string folder, string storage = "")
            {
                // GET 	cells/{name}/Worksheets/{sheetName}/pictures/{pictureNumber}/recognize?appSid={appSid}&storage={storage}&folder={folder}

                string apiUrl = string.Format(@"cells/{0}/Worksheets/{1}/pictures/{2}/recognize?storage={3}&folder={4}",
                                                name, sheetName, pictureNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                BarcodeResponse barcodeResponse = jObject.ToObject<BarcodeResponse>();
                return barcodeResponse;
            }
        }

        public class CellsChartArea
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsChartArea(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Get chart area info.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">Chart index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>ChartAreaResponse object</returns>
            public ChartAreaResponse GetChartAreaInfo(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/chartArea?appSid={appSid}&storage={storage}&folder={folder}

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/chartArea?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                ChartAreaResponse chartAreaResponse = jObject.ToObject<ChartAreaResponse>();
                return chartAreaResponse;
            }

            /// <summary>
            /// Get chart area fill format info.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">Chart index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>FillFormatResponse object</returns>
            public FillFormatResponse GetChartAreaFillFormatInfo(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/chartArea/fillFormat?appSid={appSid}&storage={storage}&folder={folder}

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/chartArea/fillFormat?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FillFormatResponse fillFormatResponse = jObject.ToObject<FillFormatResponse>();
                return fillFormatResponse;
            }

            /// <summary>
            /// Get chart area border info.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">Chart index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>LineResponse object</returns>
            public LineResponse GetChartAreaBorderInfo(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/chartArea/border?appSid={appSid}&storage={storage}&folder={folder}

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/chartArea/border?storage={3}&folder={4}",
                                 name, sheetName, chartIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                LineResponse lineResponse = jObject.ToObject<LineResponse>();
                return lineResponse;
            }
        }

        public class CellsChart
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsChart(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Get worksheet charts info. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            /// <returns>ChartsResponse object</returns>
            public CellsChartsResponse GetWorksheetChartsInfo(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsChartsResponse chartsResponse = jObject.ToObject<CellsChartsResponse>();
                return chartsResponse;
            }

            /// <summary>
            /// Add new chart to worksheet. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartType"></param>
            /// <param name="upperLeftRow">New chart upper left row.</param>
            /// <param name="upperLeftColumn">New chart upperleft column.</param>
            /// <param name="lowerRightRow">New chart lower right row.</param>
            /// <param name="lowerRightColumn">New chart lower right column.</param>
            /// <param name="area">Specifies values from which to plot the data series.</param>
            /// <param name="isVertical">Specifies whether to plot the series from a range of cell values by row or by column.</param>
            /// <param name="categoryData">Gets or sets the range of category Axis values. It can be a range of cells (such as, "d1:e10").</param>
            /// <param name="isAutoGetSerialName">Specifies whether auto update serial name.</param>
            /// <param name="title">Specifies chart title name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            public void AddNewChartToWorksheet(string name, string sheetName, ChartType chartType, int upperLeftRow, int upperLeftColumn, int lowerRightRow, int lowerRightColumn, string area, bool isVertical, string categoryData, bool isAutoGetSerialName, string title, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/charts?chartType={chartType}&appSid={appSid}&upperLeftRow={upperLeftRow}&upperLeftColumn={upperLeftColumn}&lowerRightRow={lowerRightRow}&lowerRightColumn={lowerRightColumn}&area={area}&isVertical={isVertical}&categoryData={categoryData}&isAutoGetSerialName={isAutoGetSerialName}&title={title}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts?chartType={2}&upperLeftRow={3}&upperLeftColumn={4}&lowerRightRow={5}&lowerRightColumn={6}&area={7}&isVertical={8}&categoryData={9}&isAutoGetSerialName={10}&title={11}&storage={12}&folder={13}",
                                                name, sheetName, chartType, upperLeftRow, upperLeftColumn, lowerRightRow, lowerRightColumn, area, isVertical, categoryData, isAutoGetSerialName, title, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Clear the charts. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage. </param>
            public void ClearTheCharts(string name, string sheetName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/charts?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get chart info.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartNumber">The chart number.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsChartResponse GetChartInfo(string name, string sheetName, int chartNumber, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts/{chartNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}?storage={3}&folder={4}",
                                                name, sheetName, chartNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsChartResponse CellsChartResponse = jObject.ToObject<CellsChartResponse>();
                return CellsChartResponse;
            }

            /// <summary>
            /// Get the chart in some format. 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartNumber">The chart number.</param>
            /// <param name="format">Chart conversion format.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="outPath">The output path to save the output e.g c:\out.bmp</param>
            /// <param name="storage">The document storage.</param>
            public void GetChartInSomeFormat(string name, string sheetName, int chartNumber, CellsChartOutputFormat format, string folder, string outPath, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts/{chartNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}?format={3}&storage={4}&folder={5}",
                                                name, sheetName, chartNumber, format, storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = System.IO.File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }

            /// <summary>
            /// Delete worksheet chart by index.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWorksheetChartByIndex(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get chart legend 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsChartLegendResponse GetChartLegend(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/legend?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/legend?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsChartLegendResponse CellsChartLegendResponse = jObject.ToObject<CellsChartLegendResponse>();
                return CellsChartLegendResponse;
            }

            /// <summary>
            /// Update chart legend
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="legend">CellsChartLegend</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateChartLegend(string name, string sheetName, int chartIndex, string folder, CellsChartLegend legend, string storage = "")
            {
                // POST  cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/legend?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/legend?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(legend));
            }

            /// <summary>
            ///  Show legend in chart 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ShowLegendInChart(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/legend?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/legend?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Hide legend in chart 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void HideLegendInChart(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/legend?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/legend?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Update chart title 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateChartTitle(string name, string sheetName, int chartIndex, Aspose.Cloud.CellsChart chart, string folder, string storage = "")
            {
                // POST  cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/title?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/title?storage={3}&folder={4}",
                                    name, sheetName, chartIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(chart));
            }

            /// <summary>
            /// Add chart title
            /// </summary>
            /// <param name="name">Workbook name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AddChartTitle(string name, string sheetName, int chartIndex, Aspose.Cloud.CellsChart chart, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/title?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/title?storage={3}&folder={4}",
                        name, sheetName, chartIndex, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(chart));
            }

            /// <summary>
            /// Set chart title visible 
            /// </summary>
            /// <param name="name">Workbook name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void SetChartTitleVisible(string name, string sheetName, int chartIndex, Aspose.Cloud.CellsChart chart, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/title?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/title?storage={3}&folder={4}",
                        name, sheetName, chartIndex, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(chart));
            }

            /// <summary>
            /// Hide title in chart 
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="chartIndex">The chart index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void HideTitleInChart(string name, string sheetName, int chartIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/charts/{chartIndex}/title?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/charts/{2}/title?storage={3}&folder={4}",
                                                name, sheetName, chartIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class CellsHypelink
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsHypelink(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            ///  //Get worksheet hyperlinks.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public HyperlinksResponse GetWorksheetHyperlinks(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/hyperlinks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/hyperlinks?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                HyperlinksResponse HyperlinksResponse = jObject.ToObject<HyperlinksResponse>();
                return HyperlinksResponse;
            }

            /// <summary>
            /// Add worksheet hyperlink.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="firstRow"></param>
            /// <param name="firstColumn"></param>
            /// <param name="totalRows"></param>
            /// <param name="totalColumns"></param>
            /// <param name="address"></param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsHyperlinkResponse AddWorksheetHyperlink(string name, string sheetName, int firstRow, int firstColumn, int totalRows, int totalColumns, string address, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/hyperlinks?appSid={appSid}&firstRow={firstRow}&firstColumn={firstColumn}&totalRows={totalRows}&totalColumns={totalColumns}&address={address}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/hyperlinks?firstRow={2}&firstColumn={3}&totalRows={4}&totalColumns={5}&address={6}&storage={7}&folder={8}",
                                                name, sheetName, firstRow, firstColumn, totalRows, totalColumns, address, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
                CellsHyperlinkResponse CellsHyperlinkResponse = jObject.ToObject<CellsHyperlinkResponse>();
                return CellsHyperlinkResponse;
            }

            /// <summary>
            /// Delete all hyperlinks in worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteAllHyperlinksInWorksheet(string name, string sheetName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/hyperlinks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/hyperlinks?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get worksheet hyperlink by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="hyperlinkIndex">The hyperlink's index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsHyperlinkResponse GetWorksheetHyperlinkByIndex(string name, string sheetName, int hyperlinkIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/hyperlinks/{hyperlinkIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/hyperlinks/{2}?storage={3}&folder={4}",
                                                name, sheetName, hyperlinkIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsHyperlinkResponse CellsHyperlinkResponse = jObject.ToObject<CellsHyperlinkResponse>();
                return CellsHyperlinkResponse;
            }

            /// <summary>
            /// Delete worksheet hyperlink by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="hyperlinkIndex">The hyperlink's index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWorksheetHyperlinkByIndex(string name, string sheetName, int hyperlinkIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/hyperlinks/{hyperlinkIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/hyperlinks/{2}?storage={3}&folder={4}",
                                                name, sheetName, hyperlinkIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Update worksheet hyperlink by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="hyperlinkIndex">The hyperlink's index.</param>
            /// <param name="hyperlink">CellsHyperlink</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsHyperlinkResponse UpdateWorksheetHyperlinkByIndex(string name, string sheetName, int hyperlinkIndex, CellsHyperlink hyperlink, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/hyperlinks/{hyperlinkIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/hyperlinks/{2}?storage={3}&folder={4}",
                                                name, sheetName, hyperlinkIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsHyperlinkResponse CellsHyperlinkResponse = jObject.ToObject<CellsHyperlinkResponse>();
                return CellsHyperlinkResponse;
            }
        }

        public class CellsOleObject
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsOleObject(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Get worksheet OLE objects info.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsOleObjectsResponse GetWorksheetOleObjectsInfo(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/oleobjects?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsOleObjectsResponse CellsOleObjects = jObject.ToObject<CellsOleObjectsResponse>();
                return CellsOleObjects;
            }

            /// <summary>
            /// Delete all OLE objects.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteAllOleObjects(string name, string sheetName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/oleobjects?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Add OLE object	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="upperLeftRow">Upper left row index</param>
            /// <param name="upperLeftColumn">Upper left column index</param>
            /// <param name="height">Height of oleObject, in unit of pixel</param>
            /// <param name="width">Width of oleObject, in unit of pixel</param>
            /// <param name="oleFile">OLE filename</param>
            /// <param name="imageFile">Image filename</param>
            /// <param name="oleObject">CellsOleObject</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsOleObjectResponse AddOleObject(string name, string sheetName, int upperLeftRow, int upperLeftColumn, int height, int width, string oleFile, string imageFile, Aspose.Cloud.CellsOleObject oleObject, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/oleobjects?appSid={appSid}&upperLeftRow={upperLeftRow}&upperLeftColumn={upperLeftColumn}&height={height}&width={width}&oleFile={oleFile}&imageFile={imageFile}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects?upperLeftRow={2}&upperLeftColumn={3}&height={4}&width={5}&oleFile={6}&imageFile={7}&storage={8}&folder={9}",
                                                name, sheetName, upperLeftRow, upperLeftColumn, height, width, oleFile, imageFile, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(oleObject)));
                CellsOleObjectResponse CellsOleObjectResponse = jObject.ToObject<CellsOleObjectResponse>();
                return CellsOleObjectResponse;
            }

            /// <summary>
            /// Get OLE object info
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="objectNumber">Ole object number</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsOleObjectResponse GetOleObjectInfo(string name, string sheetName, int objectNumber, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/oleobjects/{objectNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects/{2}?storage={3}&folder={4}",
                                                name, sheetName, objectNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsOleObjectResponse CellsOleObjectResponse = jObject.ToObject<CellsOleObjectResponse>();
                return CellsOleObjectResponse;
            }

            /// <summary>
            /// Get the OLE object in some format.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="objectNumber">Ole object number</param>
            /// <param name="format">Object conversion format.</param>
            /// <param name="outPath">The output file name with full path e.g. c:\out.gif</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void GetTheOleObjectInSomeFormat(string name, string sheetName, int objectNumber, CellsOleObjectFormat format, string outPath, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/oleobjects/{objectNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects/{2}?format={3}&storage={4}&folder={5}",
                                                name, sheetName, objectNumber, format, storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }

            /// <summary>
            /// Delete OLE object.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="oleObjectIndex">Ole object index</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteOleObject(string name, string sheetName, int oleObjectIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/oleobjects/{oleObjectIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects/{2}?storage={3}&folder={4}",
                                                name, sheetName, oleObjectIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Update OLE object.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="oleObjectIndex">Ole object index</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateOleObject(string name, string sheetName, int oleObjectIndex, Aspose.Cloud.CellsOleObject oleObject, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/oleobjects/{oleObjectIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/oleobjects/{2}?storage={3}&folder={4}",
                                                name, sheetName, oleObjectIndex, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(oleObject));
            }
        }

        public class CellsPicture
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsPicture(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read worksheet pictures.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsPicturesResponse ReadWorksheetPictures(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/pictures?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/pictures?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsPicturesResponse CellsPicturesResponse = jObject.ToObject<CellsPicturesResponse>();
                return CellsPicturesResponse;
            }

            /// <summary>
            /// Add a new worksheet picture.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="upperLeftRow">The image upper left row.</param>
            /// <param name="upperLeftColumn">The image upper left column.</param>
            /// <param name="lowerRightRow">The image low right row.</param>
            /// <param name="lowerRightColumn">The image low right column.</param>
            /// <param name="picturePath">The picture path, if not provided the picture data is inspected in the request body.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsPicturesResponse AddANewWorksheetPicture(string name, string sheetName, int upperLeftRow, int upperLeftColumn, int lowerRightRow, int lowerRightColumn, string picturePath, Aspose.Cloud.CellsPicture picture, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/pictures?appSid={appSid}&upperLeftRow={upperLeftRow}&upperLeftColumn={upperLeftColumn}&lowerRightRow={lowerRightRow}&lowerRightColumn={lowerRightColumn}&picturePath={picturePath}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/pictures?upperLeftRow={2}&upperLeftColumn={3}&lowerRightRow={4}&lowerRightColumn={5}&picturePath={6}&storage={7}&folder={8}",
                                                name, sheetName, upperLeftRow, upperLeftColumn, lowerRightRow, lowerRightColumn, picturePath, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(picture)));
                CellsPicturesResponse CellsPicturesResponse = jObject.ToObject<CellsPicturesResponse>();
                return CellsPicturesResponse;
            }

            /// <summary>
            /// Delete all pictures in worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteAllPicturesInWorksheet(string name, string sheetName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/pictures?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/pictures?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get worksheet picture by number.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="pictureNumber">Picture Number</param>
            /// <param name="format">Picture conversion format.</param>
            /// <param name="outPath">The output path to save the image.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void GetWorksheetPictureByNumber(string name, string sheetName, int pictureNumber, CellsPictureFormat format, string outPath, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/pictures/{pictureNumber}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/pictures/{2}?format={3}&storage={4}&folder={5}",
                                                name, sheetName, pictureNumber, format, storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }

            /// <summary>
            /// Update worksheet picture by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="pictureIndex">Picture index</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="picture">CellsPicture object</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateWorksheetPictureByIndex(string name, string sheetName, int pictureIndex, string folder, Aspose.Cloud.CellsPicture picture, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/pictures/{pictureIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/pictures/{2}?storage={3}&folder={4}",
                                                name, sheetName, pictureIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(picture)));
            }

            /// <summary>
            /// Delete a picture object in worksheet	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="pictureIndex">Picture index</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteAPictureObjectInWorksheet(string name, string sheetName, int pictureIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/pictures/{pictureIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/pictures/{2}?storage={3}&folder={4}",
                                                name, sheetName, pictureIndex, storage, folder);

                ServiceController.Get(apiUrl, AppSid, AppKey);
            }
        }

        public class CellsProperties
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsProperties(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document properties.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public DocumentPropertiesResponse ReadDocumentProperties(string name, string folder, string storage = "")
            {
                // GET 	cells/{name}/documentproperties?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/documentproperties?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                DocumentPropertiesResponse documentPropertiesResponse = jObject.ToObject<DocumentPropertiesResponse>();
                return documentPropertiesResponse;
            }

            /// <summary>
            /// Delete all custom document properties and clean built-in ones.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteAllCustomDocumentPropertiesAndCleanBuiltInOnes(string name, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/documentproperties?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/documentproperties?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Read document property by name.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public DocumentPropertyResponse ReadDocumentPropertyByName(string name, string propertyName, string folder, string storage = "")
            {
                // GET 	cells/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                DocumentPropertyResponse DocumentPropertyResponse = jObject.ToObject<DocumentPropertyResponse>();
                return DocumentPropertyResponse;
            }

            /// <summary>
            /// Set/create document property.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="property">DocumentProperty object</param>
            /// <param name="storage">The document storage.</param>
            public DocumentPropertyResponse SetCreateDocumentProperty(string name, string propertyName, string folder, DocumentProperty property, string storage = "")
            {
                // PUT 	cells/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(property)));
                DocumentPropertyResponse DocumentPropertyResponse = jObject.ToObject<DocumentPropertyResponse>();
                return DocumentPropertyResponse;
            }

            /// <summary>
            /// Delete document property.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteDocumentProperty(string name, string propertyName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class CellsSaveAs
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsSaveAs(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Convert document and save result to storage.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="newfilename">The new file name.</param>
            /// <param name="isAutoFitRows">Autofit rows.</param>
            /// <param name="isAutoFitColumns">Autofit columns.</param>
            /// <param name="saveOptions">CellsSaveOptions - Please check http://www.aspose.com/docs/display/cellscloud/saveAs+%28Controller+resource%29 and supply xml/json as string</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsSaveResultResponse ConvertDocumentAndSaveResultToStorage(string name, string newfilename, bool isAutoFitRows, bool isAutoFitColumns, string saveOptions, string folder, string storage = "")
            {
                // POST 	cells/{name}/SaveAs?appSid={appSid}&newfilename={newfilename}&isAutoFitRows={isAutoFitRows}&isAutoFitColumns={isAutoFitColumns}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/SaveAs?newfilename={1}&isAutoFitRows={2}&isAutoFitColumns={3}&storage={4}&folder={5}",
                                                name, newfilename, isAutoFitRows, isAutoFitColumns, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, saveOptions));
                CellsSaveResultResponse CellsSaveResultResponse = jObject.ToObject<CellsSaveResultResponse>();
                return CellsSaveResultResponse;
            }
        }

        public class CellsWorkbook
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsWorkbook(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Read workbook info.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="password">The document password.</param>
            /// <param name="isAutoFit">Set document rows to be autofit.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookResponse ReadWorkbookInfo(string name, string password, bool isAutoFit, string folder, string storage = "")
            {
                // GET 	cells/{name}?appSid={appSid}&format={format}&password={password}&isAutoFit={isAutoFit}&storage={storage}&folder={folder}&outPath={outPath} 

                string apiUrl = string.Format(@"cells/{0}?password={1}&isAutoFit={2}&storage={3}&folder={4}",
                                                name, password, isAutoFit, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookResponse WorkbookResponse = jObject.ToObject<WorkbookResponse>();
                return WorkbookResponse;
            }

            /// <summary>
            /// Export workbook .	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="format">The conversion format.</param>
            /// <param name="password">The document password.</param>
            /// <param name="isAutoFit">Set document rows to be autofit.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="outPath">Path to save result. It can be local (e.g. c:\out.pdf) or cloud storage path (MyFolder\out.pdf). The conversion result for TeX and HTML formats can contain multiple files so it is returned as zip archive.</param>
            /// <param name="storage">The document storage.</param>
            public void ExportWorkbook(string name, WorkbookExportFormat format, string password, bool isAutoFit, string folder, string outPath, string storage = "")
            {
                // GET 	cells/{name}?appSid={appSid}&format={format}&password={password}&isAutoFit={isAutoFit}&storage={storage}&folder={folder}&outPath={outPath} 

                string apiUrl = string.Format(@"cells/{0}?format={1}&password={2}&isAutoFit={3}&storage={4}&folder={5}&outPath={6}",
                                                name, format, password, isAutoFit, storage, folder, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.Get(apiUrl, AppSid, AppKey);
                }
            }

            /// <summary>
            /// Create new workbook using deferent methods.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="templateFile">The template file, if the data not provided default workbook is created.</param>
            /// <param name="dataFile">Smart marker data file, if the data not provided the request content is checked for the data.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookResponse CreateNewWorkbookUsingDeferentMethods(string name, string templateFile, string dataFile, string folder, string storage = "")
            {
                // PUT 	cells/{name}?appSid={appSid}&templateFile={templateFile}&dataFile={dataFile}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}?templateFile={1}&dataFile={2}&storage={3}&folder={4}",
                                                name, templateFile, dataFile, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookResponse WorkbookResponse = jObject.ToObject<WorkbookResponse>();
                return WorkbookResponse;
            }

            /// <summary>
            /// Convert workbook to some format.	
            /// </summary>
            /// <param name="format">WorkbookExportFormat</param>
            /// <param name="password">The workbook password.</param>
            /// <param name="outPath">Path to save result. Must be a Cloud storage path e.g. MyFolder\out.pdf</param>
            public void ConvertWorkbookToSomeFormat(WorkbookExportFormat format, string password, string outPath, string inputFilePath)
            {
                // PUT 	cells/convert?appSid={appSid}&format={format}&password={password}&outPath={outPath} 

                string apiUrl = string.Format(@"cells/convert?format={0}&password={1}&outPath={2}",
                                                format, password, outPath);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Encript document.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void EncriptDocument(string name, string folder, WorkbookEncryptionRequest workbookEncryptionRequest, string storage = "")
            {
                // POST 	cells/{name}/encryption?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/encryption?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(workbookEncryptionRequest));
            }

            /// <summary>
            /// Decrypt document.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DecryptDocument(string name, string folder, WorkbookEncryptionRequest workbookEncryptionRequest, string storage = "")
            {
                // DELETE 	cells/{name}/encryption?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/encryption?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(workbookEncryptionRequest));
            }

            /// <summary>
            /// Protect document.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ProtectDocument(string name, string folder, WorkbookProtection protection, string storage = "")
            {
                // POST 	cells/{name}/protection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/protection?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protection));
            }

            /// <summary>
            /// Unprotect document.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UnprotectDocument(string name, string folder, WorkbookProtection protection, string storage = "")
            {
                // DELETE 	cells/{name}/protection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/protection?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protection));
            }

            /// <summary>
            /// Read workbook default style info.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookStyleResponse ReadWorkbookDefaultStyleInfo(string name, string folder, string storage = "")
            {
                // GET 	cells/{name}/defaultstyle?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/defaultstyle?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookStyleResponse WorkbookStyleResponse = jObject.ToObject<WorkbookStyleResponse>();
                return WorkbookStyleResponse;
            }

            /// <summary>
            /// Read workbook's text items.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookTextItemsResponse ReadWorkbooksTextItems(string name, string folder, string storage = "")
            {
                // GET 	cells/{name}/textItems?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/textItems?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookTextItemsResponse WorkbookTextItemsResponse = jObject.ToObject<WorkbookTextItemsResponse>();
                return WorkbookTextItemsResponse;
            }

            /// <summary>
            /// Read workbook's names.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookNamesResponse ReadWorkbooksNames(string name, string folder, string storage = "")
            {
                // GET 	cells/{name}/names?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/names?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookNamesResponse WorkbookNamesResponse = jObject.ToObject<WorkbookNamesResponse>();
                return WorkbookNamesResponse;
            }

            /// <summary>
            /// Read workbook's name.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="nameName"></param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookNameResponse ReadWorkbooksName(string name, string nameName, string folder, string storage = "")
            {
                // GET 	cells/{name}/names/{nameName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/names/{1}?storage={2}&folder={3}",
                                                name, nameName, storage, folder);


                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorkbookNameResponse WorkbookNameResponse = jObject.ToObject<WorkbookNameResponse>();
                return WorkbookNameResponse;
            }

            /// <summary>
            /// Protect document from changes.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ProtectDocumentFromChanges(string name, string folder, ProtectDocumentRequest password, string storage = "")
            {
                // PUT 	cells/{name}/writeProtection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/writeProtection?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(password));
            }

            /// <summary>
            /// Unprotect document from changes.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UnprotectDocumentFromChanges(string name, string folder, ProtectDocumentRequest password, string storage = "")
            {
                // DELETE 	cells/{name}/writeProtection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/writeProtection?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(password));
            }

            /// <summary>
            /// Merge workbooks.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="mergeWith">The workbook to merge with. e.g. MyFolder/myFile.xlsx</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookResponse MergeWorkbooks(string name, string mergeWith, string folder, string storage = "")
            {
                // POST 	cells/{name}/merge?mergeWith={mergeWith}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/merge?mergeWith={1}&storage={2}&folder={3}",
                                                name, mergeWith, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WorkbookResponse WorkbookResponse = jObject.ToObject<WorkbookResponse>();
                return WorkbookResponse;
            }

            /// <summary>
            /// Search text.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="text">Text sample.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsTextItemsResponse SearchText(string name, string text, string folder, string storage = "")
            {
                // POST 	cells/{name}/findText?text={text}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/findText?text={1}&storage={2}&folder={3}",
                                                name, text, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                CellsTextItemsResponse CellsTextItemsResponse = jObject.ToObject<CellsTextItemsResponse>();
                return CellsTextItemsResponse;
            }

            /// <summary>
            /// Replace text.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="oldValue">The old text to replace.</param>
            /// <param name="newValue">The new text to replace by.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookReplaceTextResponse ReplaceText(string name, string oldValue, string newValue, string folder, string storage = "")
            {
                // POST 	cells/{name}/replaceText?oldValue={oldValue}&newValue={newValue}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/replaceText?oldValue={1}&newValue={2}&storage={3}&folder={4}",
                                                name, oldValue, newValue, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WorkbookReplaceTextResponse WorkbookReplaceTextResponse = jObject.ToObject<WorkbookReplaceTextResponse>();
                return WorkbookReplaceTextResponse;
            }

            /// <summary>
            /// Create an individual spreadsheet document from smart marker template file.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="xmlFile">The xml file full path</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="outPath">Path to save result</param>
            public WorkbookResponse SmartMarkerProcessingResult(string name, string xmlFile, string folder, string outPath, string storage = "")
            {
                // POST 	cells/{name}/smartmarker?appSid={appSid}&xmlFile={xmlFile}&storage={storage}&folder={folder}&outPath={outPath} 

                string apiUrl = string.Format(@"cells/{0}/smartmarker?xmlFile={1}&storage={2}&folder={3}&outPath={4}",
                                                name, xmlFile, storage, folder, (outPath.Contains(@":\") ? string.Empty : outPath));

                JObject jObject = null;
                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(xmlFile)));
                }
                else
                {
                    jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                }

                WorkbookResponse WorkbookResponse = jObject.ToObject<WorkbookResponse>();
                return WorkbookResponse;
            }

            /// <summary>
            /// Split workbook.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="format">Split format.</param>
            /// <param name="from">Start worksheet index.</param>
            /// <param name="to">End worksheet index.</param>
            /// <param name="horizontalResolution">Image horizontal resolution.</param>
            /// <param name="verticalResolution">Image vertical resolution.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorkbookSplitResponse SplitWorkbook(string name, CellsSplitFormat format, int from, int to, int horizontalResolution, int verticalResolution, string folder, string storage = "")
            {
                // POST 	cells/{name}/split?appSid={appSid}&format={format}&from={from}&to={to}&horizontalResolution={horizontalResolution}&verticalResolution={verticalResolution}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/split?format={1}&from={2}&to={3}&horizontalResolution={4}&verticalResolution={5}&storage={6}&folder={7}",
                                                name, format, from, to, horizontalResolution, verticalResolution, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WorkbookSplitResponse WorkbookSplitResponse = jObject.ToObject<WorkbookSplitResponse>();
                return WorkbookSplitResponse;
            }

            /// <summary>
            /// Import data to workbook.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ImportDataToWorkbook(string name, string folder, WorkbookImportOption importOption, string storage = "")
            {
                // POST 	cells/{name}/importdata?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/importdata?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(importOption));
            }

            /// <summary>
            /// Calculate all formulas in workbook.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void CalculateAllFormulasInWorkbook(string name, string folder, string storage = "")
            {
                // POST 	cells/{name}/calculateformula?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/calculateformula?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Autofit workbook rows.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="startRow">Start row.</param>
            /// <param name="endRow">End row.</param>
            /// <param name="onlyAuto">Only auto.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AutofitWorkbookRows(string name, int startRow, int endRow, bool onlyAuto, CellsAutoFitterOptionsRequest autoFitterOptions, string folder, string storage = "")
            {
                // POST 	cells/{name}/autofitrows?appSid={appSid}&startRow={startRow}&endRow={endRow}&onlyAuto={onlyAuto}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/autofitrows?startRow={1}&endRow={2}&onlyAuto={3}&storage={4}&folder={5}",
                                                name, startRow, endRow, onlyAuto, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(autoFitterOptions));
            }
        }

        public class CellsWorksheet
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsWorksheet(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Read worksheets info.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorksheetsResponse ReadWorksheetsInfo(string name, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorksheetsResponse WorksheetsResponse = jObject.ToObject<WorksheetsResponse>();
                return WorksheetsResponse;
            }

            /// <summary>
            /// Read worksheets info.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="verticalResolution">Image vertical resolution.</param>
            /// <param name="horizontalResolution">Image horizontal resolution.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorksheetResponse ReadWorksheetInfo(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}?appSid={appSid}&verticalResolution={verticalResolution}&horizontalResolution={horizontalResolution}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WorksheetResponse WorksheetResponse = jObject.ToObject<WorksheetResponse>();
                return WorksheetResponse;
            }

            /// <summary>
            /// Export worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="format">Export format.</param>
            /// <param name="verticalResolution">Image vertical resolution.</param>
            /// <param name="horizontalResolution">Image horizontal resolution.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="outPath">Path to save the result</param>
            /// <param name="storage">The document storage.</param>
            public void ExportWorksheet(string name, string sheetName, WorksheetExportFormat format, int verticalResolution, int horizontalResolution, string folder, string outPath, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}?appSid={appSid}&format={format}&verticalResolution={verticalResolution}&horizontalResolution={horizontalResolution}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}?format={2}&verticalResolution={3}&horizontalResolution={4}&storage={5}&folder={6}",
                                                name, sheetName, format, verticalResolution, horizontalResolution, storage, folder);

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
            }

            /// <summary>
            /// Add new worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AddNewWorksheet(string name, string sheetName, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Delete worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWorksheet(string name, string sheetName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Update worksheet property	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorksheetResponse UpdateWorksheetProperty(string name, string sheetName, Worksheet sheet, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}?folder={2}&storage={3}",
                                                name, sheetName, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(sheet)));
                WorksheetResponse WorksheetResponse = jObject.ToObject<WorksheetResponse>();
                return WorksheetResponse;
            }

            /// <summary>
            /// Change worksheet visibility.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="isVisible">New worksheet visibility value.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WorksheetResponse ChangeWorksheetVisibility(string name, string sheetName, bool isVisible, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/visible?isVisible={isVisible}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/visible?isVisible={2}&storage={3}&folder={4}",
                                                name, sheetName, isVisible, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
                WorksheetResponse WorksheetResponse = jObject.ToObject<WorksheetResponse>();
                return WorksheetResponse;
            }

            /// <summary>
            /// Move worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="moving">CellsMoveWorksheetRequest object.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void MoveWorksheet(string name, string sheetName, CellsMoveWorksheetRequest moving, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/position?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/position?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(moving));
            }

            /// <summary>
            /// Protect worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="protectParameter">CellsProtectParameter object.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ProtectWorksheet(string name, string sheetName, CellsProtectParameter protectParameter, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/protection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/protection?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protectParameter));
            }

            /// <summary>
            /// Unprotect worksheet.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="protectParameter">CellsProtectParameter object.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UnprotectWorksheet(string name, string sheetName, CellsProtectParameter protectParameter, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/protection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/protection?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protectParameter));
            }

            /// <summary>
            /// Get worksheet text items.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsTextItemsResponse GetWorksheetTextItems(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/textItems?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/textItems?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsTextItemsResponse CellsTextItemsResponse = jObject.ToObject<CellsTextItemsResponse>();
                return CellsTextItemsResponse;
            }

            /// <summary>
            /// Get worksheet comments.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsCommentsResponse GetWorksheetComments(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/comments?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/comments?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsCommentsResponse CellsCommentsResponse = jObject.ToObject<CellsCommentsResponse>();
                return CellsCommentsResponse;
            }

            /// <summary>
            /// Get worksheet comment by cell name.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="cellName">The cell name</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsCommentResponse GetWorksheetCommentByCellName(string name, string sheetName, string cellName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/comments/{cellName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/comments/{2}?storage={3}&folder={4}",
                                                name, sheetName, cellName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsCommentResponse CellsCommentResponse = jObject.ToObject<CellsCommentResponse>();
                return CellsCommentResponse;
            }

            /// <summary>
            /// Add worksheet's cell comment.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="cellName">The cell name</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AddWorksheetsCellComment(string name, string sheetName, string cellName, CellsCommentRequest comment, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/comments/{cellName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/comments/{2}?storage={3}&folder={4}",
                                                name, sheetName, cellName, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(comment));
            }

            /// <summary>
            /// Update worksheet's cell comment.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="cellName">The cell name</param>
            /// <param name="comment">CellsCommentRequest object</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateWorksheetsCellComment(string name, string sheetName, string cellName, CellsCommentRequest comment, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/comments/{cellName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/comments/{2}?storage={3}&folder={4}",
                                                name, sheetName, cellName, storage, folder);


                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(comment));
            }

            /// <summary>
            /// Delete worksheet's cell comment.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="cellName">The cell name</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWorksheetsCellComment(string name, string sheetName, string cellName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/comments/{cellName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/comments/{2}?storage={3}&folder={4}",
                                                               name, sheetName, cellName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get worksheet merged cells.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public MergedCellsResponse GetWorksheetMergedCells(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/mergedCells?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/mergedCells?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                MergedCellsResponse MergedCellsResponse = jObject.ToObject<MergedCellsResponse>();
                return MergedCellsResponse;
            }

            /// <summary>
            /// Get worksheet merged cell by its index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="mergedCellIndex">Merged cell index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public MergedCellResponse GetWorksheetMergedCellByItsIndex(string name, string sheetName, int mergedCellIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/mergedCells/{mergedCellIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/mergedCells/{2}?storage={3}&folder={4}",
                                                name, sheetName, mergedCellIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                MergedCellResponse MergedCellResponse = jObject.ToObject<MergedCellResponse>();
                return MergedCellResponse;
            }

            /// <summary>
            /// Calculate formula value.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="formula">The formula.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsForumulaValueResponse CalculateFormulaValue(string name, string sheetName, string formula, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/formulaResult?formula={formula}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/formulaResult?formula={2}&storage={3}&folder={4}",
                                                name, sheetName, formula, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsForumulaValueResponse cellsForumulaValueResponse = jObject.ToObject<CellsForumulaValueResponse>();
                return cellsForumulaValueResponse;

            }

            /// <summary>
            /// Search text.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="text">Text to search.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsTextItemsResponse SearchText(string name, string sheetName, string text, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/findText?text={text}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/findText?text={2}&storage={3}&folder={4}",
                                                name, sheetName, text, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                CellsTextItemsResponse cellsTextItemsResponse = jObject.ToObject<CellsTextItemsResponse>();
                return cellsTextItemsResponse;

            }

            /// <summary>
            /// Replace text.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="oldValue">The old text to replace.</param>
            /// <param name="newValue">The new text to replace by.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsWorksheetMatchesResponse ReplaceText(string name, string sheetName, string oldValue, string newValue, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/replaceText?oldValue={oldValue}&newValue={newValue}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/replaceText?oldValue={2}&newValue={3}&storage={4}&folder={5}",
                                                name, sheetName, oldValue, newValue, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                CellsWorksheetMatchesResponse cellsWorksheetMatchesResponse = jObject.ToObject<CellsWorksheetMatchesResponse>();
                return cellsWorksheetMatchesResponse;
            }

            /// <summary>
            /// Sort worksheet range.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="cellArea">The range to sort.</param>
            /// <param name="dataSorter">CellsDataSorterRequest object.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void SortWorksheetRange(string name, string sheetName, string cellArea, CellsDataSorterRequest dataSorter, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/sort?cellArea={cellArea}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/sort?cellArea={2}&storage={3}&folder={4}",
                                                name, sheetName, cellArea, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(dataSorter));
            }

            /// <summary>
            /// Autofit worksheet rows.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="startRow">Start row.</param>
            /// <param name="endRow">End row.</param>
            /// <param name="onlyAuto">Only auto.</param>
            /// <param name="dataSorter">CellsAutoFitterOptionsRequest object.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AutofitWorksheetRows(string name, string sheetName, int startRow, int endRow, bool onlyAuto, CellsAutoFitterOptionsRequest autoFitterOptions, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/autofitrows?appSid={appSid}&startRow={startRow}&endRow={endRow}&onlyAuto={onlyAuto}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/autofitrows?startRow={2}&endRow={3}&onlyAuto={4}&storage={5}&folder={6}",
                                                name, sheetName, startRow, endRow, onlyAuto, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(autoFitterOptions));
            }

            /// <summary>
            /// Set worksheet background image.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="imagefile">Local image file name with full path e.g. c:\sample.jpg</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void SetWorksheetBackgroundImage(string name, string sheetName, string imagefile, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/background?imagefile={imagefile}&appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/background?folder={2}&storage={3}",
                                                name, sheetName, folder, storage);

                ServiceController.Put(apiUrl, AppSid, AppKey, File.ReadAllBytes(imagefile));
            }

            /// <summary>
            /// Set worksheet background image.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWorksheetBackgroundImage(string name, string sheetName, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/background?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/background?folder={2}&storage={3}",
                                                name, sheetName, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Set freeze panes	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="row"></param>
            /// <param name="column"></param>
            /// <param name="freezedRows"></param>
            /// <param name="freezedColumns"></param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void SetFreezePanes(string name, string sheetName, int row, int column, int freezedRows, int freezedColumns, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/freezepanes?appSid={appSid}&row={row}&column={column}&freezedRows={freezedRows}&freezedColumns={freezedColumns}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/freezepanes?row={2}&column={3}&freezedRows={4}&freezedColumns={5}&folder={6}&storage={7}",
                                                name, sheetName, row, column, freezedRows, freezedColumns, folder, storage);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Unfreeze panes
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="row"></param>
            /// <param name="column"></param>
            /// <param name="freezedRows"></param>
            /// <param name="freezedColumns"></param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UnfreezePanes(string name, string sheetName, int row, int column, int freezedRows, int freezedColumns, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/freezepanes?appSid={appSid}&row={row}&column={column}&freezedRows={freezedRows}&freezedColumns={freezedColumns}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/freezepanes?row={2}&column={3}&freezedRows={4}&freezedColumns={5}&folder={6}&storage={7}",
                                                name, sheetName, row, column, freezedRows, freezedColumns, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Copy worksheet
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="sourceSheet">Source worksheet name</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void CopyWorksheet(string name, string sheetName, string sourceSheet, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/copy?sourceSheet={sourceSheet}&appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/copy?sourceSheet={2}&folder={3}&storage={4}",
                                                name, sheetName, sourceSheet, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Rename worksheet	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="newname">New worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void RenameWorksheet(string name, string sheetName, string newname, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/rename?newname={newname}&appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/rename?newname={2}&folder={3}&storage={4}",
                                                name, sheetName, newname, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

        }

        public class CellsWorksheetValidation
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CellsWorksheetValidation(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Get worksheet validations.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsValidationsResponse GetWorksheetValidations(string name, string sheetName, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/validations?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/validations?storage={2}&folder={3}",
                                                name, sheetName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsValidationsResponse cellsValidationsResponse = jObject.ToObject<CellsValidationsResponse>();
                return cellsValidationsResponse;
            }

            /// <summary>
            /// Add worksheet validation at index.
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="range">Specified cells area</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsValidationResponse AddWorksheetValidationAtIndex(string name, string sheetName, string range, string folder, string storage = "")
            {
                // PUT 	cells/{name}/worksheets/{sheetName}/validations?appSid={appSid}&range={range}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/validations?range={2}&storage={3}&folder={4}",
                                                name, sheetName, range, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
                CellsValidationResponse cellsValidationResponse = jObject.ToObject<CellsValidationResponse>();
                return cellsValidationResponse;
            }

            /// <summary>
            /// Get worksheet validation by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="validationIndex">The validation index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CellsValidationResponse GetWorksheetValidationByIndex(string name, string sheetName, int validationIndex, string folder, string storage = "")
            {
                // GET 	cells/{name}/worksheets/{sheetName}/validations/{validationIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/validations/{2}?storage={3}&folder={4}",
                                                name, sheetName, validationIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CellsValidationResponse cellsValidationResponse = jObject.ToObject<CellsValidationResponse>();
                return cellsValidationResponse;

            }

            /// <summary>
            /// Update worksheet validation by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="validationIndex">The validation index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateWorksheetValidationByIndex(string name, string sheetName, int validationIndex, string folder, string storage = "")
            {
                // POST 	cells/{name}/worksheets/{sheetName}/validations/{validationIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/validations/{2}?storage={3}&folder={4}",
                                                name, sheetName, validationIndex, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Delete worksheet validation by index.	
            /// </summary>
            /// <param name="name">Document name.</param>
            /// <param name="sheetName">Worksheet name.</param>
            /// <param name="validationIndex">The validation index.</param>
            /// <param name="folder">Document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWorksheetValidationByIndex(string name, string sheetName, int validationIndex, string folder, string storage = "")
            {
                // DELETE 	cells/{name}/worksheets/{sheetName}/validations/{validationIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"cells/{0}/worksheets/{1}/validations/{2}?storage={3}&folder={4}",
                                                name, sheetName, validationIndex, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }
    }

    public class Style
    {
        public Link link { get; set; }
    }

    public class WorkbookCell
    {
        public string Name { get; set; }
        public int Row { get; set; }
        public int Column { get; set; }
        public string Value { get; set; }
        public string Type { get; set; }
        public string Formula { get; set; }
        public bool IsFormula { get; set; }
        public bool IsMerged { get; set; }
        public bool IsArrayHeader { get; set; }
        public bool IsInArray { get; set; }
        public bool IsErrorValue { get; set; }
        public bool IsInTable { get; set; }
        public bool IsStyleSet { get; set; }
        public string HtmlString { get; set; }
        public Style Style { get; set; }
        public string Worksheet { get; set; }
        public Link link { get; set; }
    }

    public class WorkbookCellResponse
    {
        public WorkbookCell Cell { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Font
    {
        public Color Color { get; set; }
        public double DoubleSize { get; set; }
        public bool IsBold { get; set; }
        public bool IsItalic { get; set; }
        public bool IsStrikeout { get; set; }
        public bool IsSubscript { get; set; }
        public bool IsSuperscript { get; set; }
        public string Name { get; set; }
        public int Size { get; set; }
        public string Underline { get; set; }
    }

    public class WorkbookStyleBorderCollection
    {
        public string LineStyle { get; set; }
        public Color Color { get; set; }
        public string BorderType { get; set; }
    }

    public class WorkbookStyle
    {
        public Font Font { get; set; }
        public string Name { get; set; }
        public string CultureCustom { get; set; }
        public string Custom { get; set; }
        public Color BackgroundColor { get; set; }
        public Color ForegroundColor { get; set; }
        public bool IsFormulaHidden { get; set; }
        public bool IsDateTime { get; set; }
        public bool IsTextWrapped { get; set; }
        public bool IsGradient { get; set; }
        public bool IsLocked { get; set; }
        public bool IsPercent { get; set; }
        public bool ShrinkToFit { get; set; }
        public int IndentLevel { get; set; }
        public int Number { get; set; }
        public int RotationAngle { get; set; }
        public string Pattern { get; set; }
        public string TextDirection { get; set; }
        public string VerticalAlignment { get; set; }
        public string HorizontalAlignment { get; set; }
        public List<WorkbookStyleBorderCollection> BorderCollection { get; set; }
        public Link link { get; set; }
    }

    public class HyperlinkList
    {
        public Link link { get; set; }
    }

    public class Hyperlinks
    {
        public int Count { get; set; }
        public List<HyperlinkList> HyperlinkList { get; set; }
        public Link link { get; set; }
    }

    public class HyperlinksResponse
    {
        public Hyperlinks Hyperlinks { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsHyperlinkResponse
    {
        public CellsHyperlink Hyperlink { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsHyperlinkArea
    {
        public int EndColumn { get; set; }
        public int EndRow { get; set; }
        public int StartColumn { get; set; }
        public int StartRow { get; set; }
    }

    public class CellsHyperlink
    {
        public string Address { get; set; }
        public CellsHyperlinkArea Area { get; set; }
        public string ScreenTip { get; set; }
        public string TextToDisplay { get; set; }
        public Link link { get; set; }
    }

    public class OleObjectList
    {
        public Link link { get; set; }
    }

    public class CellsOleObjects
    {
        public List<OleObjectList> OleObjectList { get; set; }
        public Link link { get; set; }
    }

    public class CellsOleObjectsResponse
    {
        public CellsOleObjects OleObjects { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsOleObjectResponse
    {
        public int Code { get; set; }
        public string Status { get; set; }
        public CellsOleObject OleObject { get; set; }
    }

    public class CellsOleObject
    {
        public bool DisplayAsIcon { get; set; }
        public string FileFormatType { get; set; }
        public string ImageSourceFullName { get; set; }
        public bool IsAutoSize { get; set; }
        public bool IsLink { get; set; }
        public string ProgID { get; set; }
        public string SourceFullName { get; set; }
        public string Name { get; set; }
        public string MsoDrawingType { get; set; }
        public string AutoShapeType { get; set; }
        public string Placement { get; set; }
        public int UpperLeftRow { get; set; }
        public int Top { get; set; }
        public int UpperLeftColumn { get; set; }
        public int Left { get; set; }
        public int LowerRightRow { get; set; }
        public int Bottom { get; set; }
        public int LowerRightColumn { get; set; }
        public int Right { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public double RotationAngle { get; set; }
        public string HtmlText { get; set; }
        public string Text { get; set; }
        public string AlternativeText { get; set; }
        public string TextHorizontalAlignment { get; set; }
        public string TextHorizontalOverflow { get; set; }
        public string TextOrientationType { get; set; }
        public string TextVerticalAlignment { get; set; }
        public string TextVerticalOverflow { get; set; }
        public bool IsGroup { get; set; }
        public bool IsHidden { get; set; }
        public bool IsLockAspectRatio { get; set; }
        public bool IsLocked { get; set; }
        public bool IsPrintable { get; set; }
        public bool IsTextWrapped { get; set; }
        public bool IsWordArt { get; set; }
        public string LinkedCell { get; set; }
        public int ZOrderPosition { get; set; }
        public Link link { get; set; }
    }

    public class PictureList
    {
        public Link link { get; set; }
    }

    public class CellsPictures
    {
        public List<PictureList> PictureList { get; set; }
        public Link link { get; set; }
    }

    public class CellsPicturesResponse
    {
        public CellsPictures Pictures { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsPicture
    {
        public Color BorderLineColor { get; set; }
        public double BorderWeight { get; set; }
        public int OriginalHeight { get; set; }
        public int OriginalWidth { get; set; }
        public string ImageFormat { get; set; }
        public string SourceFullName { get; set; }
        public string Name { get; set; }
        public string MsoDrawingType { get; set; }
        public string AutoShapeType { get; set; }
        public string Placement { get; set; }
        public int UpperLeftRow { get; set; }
        public int Top { get; set; }
        public int UpperLeftColumn { get; set; }
        public int Left { get; set; }
        public int LowerRightRow { get; set; }
        public int Bottom { get; set; }
        public int LowerRightColumn { get; set; }
        public int Right { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public double RotationAngle { get; set; }
        public string HtmlText { get; set; }
        public string Text { get; set; }
        public string AlternativeText { get; set; }
        public string TextHorizontalAlignment { get; set; }
        public string TextHorizontalOverflow { get; set; }
        public string TextOrientationType { get; set; }
        public string TextVerticalAlignment { get; set; }
        public string TextVerticalOverflow { get; set; }
        public bool IsGroup { get; set; }
        public bool IsHidden { get; set; }
        public bool IsLockAspectRatio { get; set; }
        public bool IsLocked { get; set; }
        public bool IsPrintable { get; set; }
        public bool IsTextWrapped { get; set; }
        public bool IsWordArt { get; set; }
        public string LinkedCell { get; set; }
        public int ZOrderPosition { get; set; }
        public Link link { get; set; }
    }

    public class CellsPictureResponse
    {
        public CellsPicture Picture { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsSaveResult
    {
        public Link SourceDocument { get; set; }
        public Link DestDocument { get; set; }
        public List<Link> AdditionalItems { get; set; }
    }

    public class CellsSaveResultResponse
    {
        public CellsSaveResult SaveResult { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Workbook
    {
        public string FileName { get; set; }
        public Link link { get; set; }
        public List<Link> Links { get; set; }
        public Worksheets Worksheets { get; set; }
        public WorkbookDefaultStyle DefaultStyle { get; set; }
        public WorkbookDocumentProperties DocumentProperties { get; set; }
        public WorkbookNames Names { get; set; }
        public string IsWriteProtected { get; set; }
        public string IsProtected { get; set; }
        public string IsEncryption { get; set; }
        public object Password { get; set; }
    }

    public class WorkbookResponse
    {
        public Workbook Workbook { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorkbookEncryptionRequest
    {
        public WorkbookEncryptionRequest(WorkbookEncryptionType workbookEncryptionType, int keyLength, string password)
        {
            EncryptionType = workbookEncryptionType.ToString();
            KeyLength = keyLength;
            Password = password;
        }

        public string EncryptionType { get; set; }
        public int KeyLength { get; set; }
        public string Password { get; set; }
    }

    public class WorkbookProtection
    {
        public string ProtectionType { get; set; }
        public string Password { get; set; }
    }

    public class WorkbookTextItemList
    {
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WorkbookTextItems
    {
        public List<WorkbookTextItemList> TextItemList { get; set; }
        public Link link { get; set; }
    }

    public class WorkbookTextItemsResponse
    {
        public WorkbookTextItems TextItems { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorkbookNameList
    {
        public Link link { get; set; }
    }

    public class WorkbookNames
    {
        public int Count { get; set; }
        public List<WorkbookNameList> NameList { get; set; }
        public Link link { get; set; }
    }

    public class WorkbookNamesResponse
    {
        public WorkbookNames Names { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorkbookName
    {
        public string Comment { get; set; }
        public int WorksheetIndex { get; set; }
        public bool IsReferred { get; set; }
        public bool IsVisible { get; set; }
        public string R1C1RefersTo { get; set; }
        public string RefersTo { get; set; }
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WorkbookNameResponse
    {
        public WorkbookName Name { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class ProtectDocumentRequest
    {
        public string Password { get; set; }
    }

    public class WorkbookReplaceTextResponse
    {
        public int Matches { get; set; }
        public Workbook Workbook { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorkbookDefaultStyle
    {
        public Link link { get; set; }
    }

    public class WorkbookDocumentProperties
    {
        public Link link { get; set; }
    }

    public class WorkbookDocument
    {
        public int Id { get; set; }
        public Link link { get; set; }
    }

    public class WorkbookSplitResult
    {
        public List<WorkbookDocument> Documents { get; set; }
    }

    public class WorkbookSplitResponse
    {
        public WorkbookSplitResult Result { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorkbookImportOption
    {
        // to do
    }

    public class WorksheetList
    {
        public Link link { get; set; }
    }

    public class Worksheets
    {
        public List<WorksheetList> WorksheetList { get; set; }
        public Link link { get; set; }
    }

    public class WorksheetsResponse
    {
        public Worksheets Worksheets { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorksheetResponse
    {
        public int Code { get; set; }
        public string Status { get; set; }
        public Worksheet Worksheet { get; set; }
    }

    public class Worksheet
    {
        public List<Link> Links { get; set; }
        public bool DisplayRightToLeft { get; set; }
        public bool DisplayZeros { get; set; }
        public int FirstVisibleColumn { get; set; }
        public int FirstVisibleRow { get; set; }
        public string Name { get; set; }
        public int Index { get; set; }
        public bool IsGridlinesVisible { get; set; }
        public bool IsOutlineShown { get; set; }
        public bool IsPageBreakPreview { get; set; }
        public bool IsVisible { get; set; }
        public bool IsProtected { get; set; }
        public bool IsRowColumnHeadersVisible { get; set; }
        public bool IsRulerVisible { get; set; }
        public bool IsSelected { get; set; }
        public Color TabColor { get; set; }
        public bool TransitionEntry { get; set; }
        public bool TransitionEvaluation { get; set; }
        public string Type { get; set; }
        public string ViewType { get; set; }
        public string VisibilityType { get; set; }
        public int Zoom { get; set; }
        public Cells Cells { get; set; }
        public CellsCharts Charts { get; set; }
        public CellsAutoShapes AutoShapes { get; set; }
        public CellsOleObjects OleObjects { get; set; }
        public CellsComments Comments { get; set; }
        public CellsPictures Pictures { get; set; }
        public CellsMergedCells MergedCells { get; set; }
        public CellsValidations Validations { get; set; }
        public CellsConditionalFormattings ConditionalFormattings { get; set; }
        public CellsHyperlinks Hyperlinks { get; set; }
    }

    public class Cells
    {
        public int MaxRow { get; set; }
        public int MaxColumn { get; set; }
        public int CellCount { get; set; }
        public Rows Rows { get; set; }
        public WorksheetColumns Columns { get; set; }
        public List<CellList> CellList { get; set; }
        public Link link { get; set; }
    }

    public class CellsCharts
    {
        public List<ChartList> ChartList { get; set; }
        public Link link { get; set; }
    }

    public class CellsAutoShapes
    {
        public List<AutoShapeList> AutoShapeList { get; set; }
        public Link link { get; set; }
    }

    public class CellsComments
    {
        public List<CellsCommentList> CommentList { get; set; }
        public Link link { get; set; }
    }

    public class CellsMergedCells
    {
        public int Count { get; set; }
        public List<MergedCellList> MergedCellList { get; set; }
        public Link link { get; set; }
    }

    public class CellsValidations
    {
        public int Count { get; set; }
        public List<ValidationList> ValidationList { get; set; }
        public Link link { get; set; }
    }

    public class CellsConditionalFormattings
    {
        public Link link { get; set; }
    }

    public class CellsHyperlinks
    {
        public Link link { get; set; }
    }

    public class CellsMoveWorksheetRequest
    {
        public string DestinationWorksheet { get; set; }
        public int Position { get; set; }
    }

    public class CellsProtectParameter
    {
        public CellsProtectParameter(CellsProtectionType protectionType)
        {
            ProtectionType = protectionType.ToString();
        }

        public string ProtectionType { get; set; }
        public string Password { get; set; }
        public List<string> AllowEditArea { get; set; }
        public string AllowDeletingColumn { get; set; }
        public string AllowDeletingRow { get; set; }
        public string AllowFiltering { get; set; }
        public string AllowFormattingCell { get; set; }
        public string AllowFormattingColumn { get; set; }
        public string AllowFormattingRow { get; set; }
        public string AllowInsertingColumn { get; set; }
        public string AllowInsertingHyperlink { get; set; }
        public string AllowInsertingRow { get; set; }
        public string AllowSelectingLockedCell { get; set; }
        public string AllowSelectingUnlockedCell { get; set; }
        public string AllowSorting { get; set; }
        public string AllowUsingPivotTable { get; set; }
    }

    public class CellsTextItemList
    {
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class CellsTextItems
    {
        public List<CellsTextItemList> TextItemList { get; set; }
        public Link link { get; set; }
    }

    public class CellsTextItemsResponse
    {
        public CellsTextItems TextItems { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsCommentList
    {
        public Link link { get; set; }
    }

    public class CellsCommentsResponse
    {
        public CellsComments Comments { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsComment
    {
        public string CellName { get; set; }
        public string Author { get; set; }
        public string HtmlNote { get; set; }
        public string Note { get; set; }
        public bool AutoSize { get; set; }
        public bool IsVisible { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public string TextHorizontalAlignment { get; set; }
        public string TextOrientationType { get; set; }
        public string TextVerticalAlignment { get; set; }
        public Link link { get; set; }
    }

    public class CellsCommentResponse
    {
        public CellsComment Comment { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsCommentRequest
    {
        public string CellName { get; set; }
        public string Author { get; set; }
        public string HtmlNote { get; set; }
        public string Note { get; set; }
        public bool AutoSize { get; set; }
        public bool IsVisible { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public string TextHorizontalAlignment { get; set; }
        public string TextOrientationType { get; set; }
        public string TextVerticalAlignment { get; set; }
        public Link link { get; set; }
    }

    public class MergedCellList
    {
        public Link link { get; set; }
    }

    public class MergedCellsResponse
    {
        public CellsMergedCells MergedCells { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class MergedCell
    {
        public int EndColumn { get; set; }
        public int EndRow { get; set; }
        public int StartColumn { get; set; }
        public int StartRow { get; set; }
        public Link link { get; set; }
    }

    public class MergedCellResponse
    {
        public MergedCell MergedCell { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsFormulaValue
    {
        public int ValueType { get; set; }
        public string Value { get; set; }
    }

    public class CellsForumulaValueResponse
    {
        public CellsFormulaValue Value { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsWorksheet
    {
        public Link link { get; set; }
    }

    public class CellsWorksheetMatchesResponse
    {
        public int Matches { get; set; }
        public CellsWorksheet Worksheet { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class CellsKeyList
    {
        public int Key { get; set; }
        public string SortOrder { get; set; }
    }

    public class CellsDataSorterRequest
    {
        public bool CaseSensitive { get; set; }
        public bool HasHeaders { get; set; }
        public List<CellsKeyList> KeyList { get; set; }
        public bool SortLeftToRight { get; set; }
    }

    public class CellsAutoFitterOptionsRequest
    {
        public bool AutoFitMergedCells { get; set; }
        public bool IgnoreHidden { get; set; }
        public bool OnlyAuto { get; set; }
    }

    public class CellsValidationAreaList
    {
        public int EndColumn { get; set; }
        public int EndRow { get; set; }
        public int StartColumn { get; set; }
        public int StartRow { get; set; }
    }

    public class CellsValidation
    {
        public string AlertStyle { get; set; }
        public List<CellsValidationAreaList> AreaList { get; set; }
        public string ErrorMessage { get; set; }
        public string ErrorTitle { get; set; }
        public string Formula1 { get; set; }
        public string Formula2 { get; set; }
        public bool IgnoreBlank { get; set; }
        public bool InCellDropDown { get; set; }
        public string InputMessage { get; set; }
        public string InputTitle { get; set; }
        public string Operator { get; set; }
        public bool ShowError { get; set; }
        public bool ShowInput { get; set; }
        public string Type { get; set; }
        public string Value1 { get; set; }
        public string Value2 { get; set; }
        public Link link { get; set; }
    }

    public class CellsValidationResponse
    {
        public CellsValidation Validation { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class ValidationList
    {
        public Link link { get; set; }
    }

    public class CellsValidationsResponse
    {
        public CellsValidations Validations { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class LegendEntries
    {
        public Link link { get; set; }
    }

    public class ThemeColor
    {
        public string ColorType { get; set; }
        public double Tint { get; set; }
    }

    public class CellsColor
    {
        public Color Color { get; set; }
        public int ColorIndex { get; set; }
        public bool IsShapeColor { get; set; }
        public ThemeColor ThemeColor { get; set; }
        public string Type { get; set; }
    }

    public class SolidFill
    {
        public Color Color { get; set; }
        public CellsColor CellsColor { get; set; }
        public double Transparency { get; set; }
    }

    public class TilePicOption
    {
        public double OffsetX { get; set; }
        public double OffsetY { get; set; }
        public double ScaleX { get; set; }
        public double ScaleY { get; set; }
        public string AlignmentType { get; set; }
        public string MirrorType { get; set; }
    }

    public class PicFormatOption
    {
        public string Type { get; set; }
        public double Scale { get; set; }
        public double Left { get; set; }
        public double Right { get; set; }
        public double Top { get; set; }
        public double Bottom { get; set; }
    }

    public class Image
    {
        public Link link { get; set; }
    }

    public class TextureFill
    {
        public string Type { get; set; }
        public double Transparency { get; set; }
        public double Scale { get; set; }
        public TilePicOption TilePicOption { get; set; }
        public PicFormatOption PicFormatOption { get; set; }
        public Image Image { get; set; }
    }

    public class GradientFill
    {
        public string FillType { get; set; }
        public string DirectionType { get; set; }
        public double Angle { get; set; }
        public List<GradientStop> GradientStops { get; set; }
    }

    public class FillFormat
    {
        public string Type { get; set; }
        public SolidFill SolidFill { get; set; }
        public PatternFill PatternFill { get; set; }
        public TextureFill TextureFill { get; set; }
        public GradientFill GradientFill { get; set; }
        public string ImageData { get; set; }
    }

    public class CellsChartLegend
    {
        public string Position { get; set; }
        public LegendEntries LegendEntries { get; set; }
        public Area Area { get; set; }
        public bool AutoScaleFont { get; set; }
        public string BackgroundMode { get; set; }
        public Border Border { get; set; }
        public Font Font { get; set; }
        public bool IsAutomaticSize { get; set; }
        public object IsInnerMode { get; set; }
        public bool Shadow { get; set; }
        public object ShapeProperties { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public Link link { get; set; }
    }

    public class CellsChartLegendResponse
    {
        public CellsChartLegend Legend { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class BackWall
    {
        public Link link { get; set; }
    }

    public class CategoryAxis
    {
        public Link link { get; set; }
    }

    public class ChartDataTable
    {
        public Link link { get; set; }
    }

    public class ChartObject
    {
        public Link link { get; set; }
    }

    public class NSeries
    {
        public Link link { get; set; }
    }

    public class PageSetup
    {
        public Link link { get; set; }
    }

    public class PlotArea
    {
        public Link link { get; set; }
    }

    public class SecondCategoryAxis
    {
        public Link link { get; set; }
    }

    public class SecondValueAxis
    {
        public Link link { get; set; }
    }

    public class Shapes
    {
        public Link link { get; set; }
    }

    public class SideWall
    {
        public Link link { get; set; }
    }

    public class Title
    {
        public Link link { get; set; }
    }

    public class ValueAxis
    {
        public Link link { get; set; }
    }

    public class CellsChart
    {
        public bool AutoScaling { get; set; }
        public BackWall BackWall { get; set; }
        public CategoryAxis CategoryAxis { get; set; }
        public ChartArea ChartArea { get; set; }
        public ChartDataTable ChartDataTable { get; set; }
        public ChartObject ChartObject { get; set; }
        public int DepthPercent { get; set; }
        public int Elevation { get; set; }
        public int FirstSliceAngle { get; set; }
        public object Floor { get; set; }
        public int GapDepth { get; set; }
        public int GapWidth { get; set; }
        public int HeightPercent { get; set; }
        public bool HidePivotFieldButtons { get; set; }
        public bool Is3D { get; set; }
        public bool IsRectangularCornered { get; set; }
        public CellsChartLegend Legend { get; set; }
        public string Name { get; set; }
        public NSeries NSeries { get; set; }
        public PageSetup PageSetup { get; set; }
        public int Perspective { get; set; }
        public object PivotSource { get; set; }
        public string Placement { get; set; }
        public PlotArea PlotArea { get; set; }
        public string PlotEmptyCellsType { get; set; }
        public bool PlotVisibleCells { get; set; }
        public string PrintSize { get; set; }
        public bool RightAngleAxes { get; set; }
        public int RotationAngle { get; set; }
        public SecondCategoryAxis SecondCategoryAxis { get; set; }
        public SecondValueAxis SecondValueAxis { get; set; }
        public object SeriesAxis { get; set; }
        public Shapes Shapes { get; set; }
        public bool ShowDataTable { get; set; }
        public bool ShowLegend { get; set; }
        public SideWall SideWall { get; set; }
        public bool SizeWithWindow { get; set; }
        public int Style { get; set; }
        public Title Title { get; set; }
        public string Type { get; set; }
        public ValueAxis ValueAxis { get; set; }
        public object Walls { get; set; }
        public bool WallsAndGridlines2D { get; set; }
        public Link link { get; set; }
    }

    public class CellsChartResponse
    {
        public CellsChart Chart { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class ChartList
    {
        public Link link { get; set; }
    }

    public class CellsChartsResponse
    {
        public CellsCharts Charts { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class GradientStop
    {
        public Color Color { get; set; }
        public double Position { get; set; }
        public double Transparency { get; set; }
    }

    public class Line
    {
        public string BeginArrowLength { get; set; }
        public string BeginArrowWidth { get; set; }
        public string BeginType { get; set; }
        public string CapType { get; set; }
        public Color Color { get; set; }
        public string CompoundType { get; set; }
        public string DashType { get; set; }
        public string EndArrowLength { get; set; }
        public string EndArrowWidth { get; set; }
        public string EndType { get; set; }
        public GradientFill GradientFill { get; set; }
        public bool IsAuto { get; set; }
        public bool IsAutomaticColor { get; set; }
        public bool IsVisible { get; set; }
        public string JoinType { get; set; }
        public string Style { get; set; }
        public double Transparency { get; set; }
        public string Weight { get; set; }
        public double WeightPt { get; set; }
    }

    public class LineResponse
    {
        public Line Line { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class BackgroundCellsColor
    {
        public Color Color { get; set; }
        public int ColorIndex { get; set; }
        public bool IsShapeColor { get; set; }
        public ThemeColor ThemeColor { get; set; }
        public string Type { get; set; }
    }

    public class ForegroundCellsColor
    {
        public Color Color { get; set; }
        public int ColorIndex { get; set; }
        public bool IsShapeColor { get; set; }
        public ThemeColor ThemeColor { get; set; }
        public string Type { get; set; }
    }

    public class PatternFill
    {
        public string Pattern { get; set; }
        public BackgroundCellsColor BackgroundCellsColor { get; set; }
        public ForegroundCellsColor ForegroundCellsColor { get; set; }
        public Color ForegroundColor { get; set; }
        public Color BackgroundColor { get; set; }
        public double BackTransparency { get; set; }
        public double ForeTransparency { get; set; }
    }

    public class Link
    {
        public string Href { get; set; }
        public string Rel { get; set; }
        public string Type { get; set; }
        public string Title { get; set; }
    }

    public class FillFormatResponse
    {
        public FillFormat FillFormat { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Area
    {
        public Color BackgroundColor { get; set; }
        public FillFormat FillFormat { get; set; }
        public Color ForegroundColor { get; set; }
        public string Formatting { get; set; }
        public bool InvertIfNegative { get; set; }
        public double Transparency { get; set; }
    }

    public class Border
    {
        public string BeginArrowLength { get; set; }
        public string BeginArrowWidth { get; set; }
        public string BeginType { get; set; }
        public string CapType { get; set; }
        public Color Color { get; set; }
        public string CompoundType { get; set; }
        public string DashType { get; set; }
        public string EndArrowLength { get; set; }
        public string EndArrowWidth { get; set; }
        public string EndType { get; set; }
        public object GradientFill { get; set; }
        public bool IsAuto { get; set; }
        public bool IsAutomaticColor { get; set; }
        public bool IsVisible { get; set; }
        public string JoinType { get; set; }
        public string Style { get; set; }
        public double Transparency { get; set; }
        public string Weight { get; set; }
        public double WeightPt { get; set; }
    }

    public class ShapeProperty
    {
        public Link link { get; set; }
    }

    public class ChartArea
    {
        public Area Area { get; set; }
        public bool AutoScaleFont { get; set; }
        public string BackgroundMode { get; set; }
        public Border Border { get; set; }
        public Font Font { get; set; }
        public bool IsAutomaticSize { get; set; }
        public bool IsInnerMode { get; set; }
        public bool Shadow { get; set; }
        public List<ShapeProperty> ShapeProperties { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public Link link { get; set; }
    }

    public class ChartAreaResponse
    {
        public ChartArea ChartArea { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class AutoShape
    {
        public string Name { get; set; }
        public string MsoDrawingType { get; set; }
        public string AutoShapeType { get; set; }
        public string Placement { get; set; }
        public int UpperLeftRow { get; set; }
        public int Top { get; set; }
        public int UpperLeftColumn { get; set; }
        public int Left { get; set; }
        public int LowerRightRow { get; set; }
        public int Bottom { get; set; }
        public int LowerRightColumn { get; set; }
        public int Right { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public int X { get; set; }
        public int Y { get; set; }
        public double RotationAngle { get; set; }
        public string HtmlText { get; set; }
        public object Text { get; set; }
        public string AlternativeText { get; set; }
        public string TextHorizontalAlignment { get; set; }
        public string TextHorizontalOverflow { get; set; }
        public string TextOrientationType { get; set; }
        public string TextVerticalAlignment { get; set; }
        public string TextVerticalOverflow { get; set; }
        public bool IsGroup { get; set; }
        public bool IsHidden { get; set; }
        public bool IsLockAspectRatio { get; set; }
        public bool IsLocked { get; set; }
        public bool IsPrintable { get; set; }
        public bool IsTextWrapped { get; set; }
        public bool IsWordArt { get; set; }
        public object LinkedCell { get; set; }
        public int ZOrderPosition { get; set; }
        public Link link { get; set; }
    }

    public class AutoShapeResponse
    {
        public AutoShape AutoShape { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class AutoShapeList
    {
        public Link link { get; set; }
    }

    public class AutoShapesResponse
    {
        public CellsAutoShapes AutoShapes { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorksheetRow
    {
        public int GroupLevel { get; set; }
        public double Height { get; set; }
        public int Index { get; set; }
        public bool IsBlank { get; set; }
        public bool IsHeightMatched { get; set; }
        public bool IsHidden { get; set; }
        public Link link { get; set; }
    }

    public class WorksheetRowResponse
    {
        public WorksheetRow Row { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorksheetRowsList
    {
        public Link link { get; set; }
    }

    public class WorksheetRowsResponse
    {
        public Rows Rows { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Color
    {
        public Color(int a, int r, int g, int b)
        {
            A = a; B = b; R = r; G = g;
        }

        public int A { get; set; }
        public int R { get; set; }
        public int G { get; set; }
        public int B { get; set; }
    }

    public class WorkbookStyleResponse
    {
        public WorkbookStyle Style { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Rows
    {
        public int MaxRow { get; set; }
        public int RowsCount { get; set; }
        public List<WorksheetRowsList> RowsList { get; set; }
        public Link link { get; set; }
    }

    public class CellList
    {
        public Link link { get; set; }
    }

    public class CellsResponse
    {
        public Cells Cells { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorksheetColumn
    {
        public int GroupLevel { get; set; }
        public int Index { get; set; }
        public bool IsHidden { get; set; }
        public double Width { get; set; }
        public Link link { get; set; }
    }

    public class WorksheetColumnResponse
    {
        public WorksheetColumn Column { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorksheetColumns
    {
        public int MaxColumn { get; set; }
        public int ColumnsCount { get; set; }
        public List<WorksheetColumnsList> ColumnsList { get; set; }
        public Link link { get; set; }
    }

    public class WorksheetColumnsResponse
    {
        public WorksheetColumns Columns { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorksheetColumnsList
    {
        public Link link { get; set; }
    }

    public enum CellsChartOutputFormat
    {
        Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum CellsAutoshapeFormat
    {
        Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum ChartType
    {
        Area,
        AreaStacked,
        Area100PercentStacked,
        Area3D,
        Area3DStacked,
        Area3D100PercentStacked,
        Bar,
        BarStacked,
        Bar100PercentStacked,
        Bar3DClustered,
        Bar3DStacked,
        Bar3D100PercentStacked,
        Bubble,
        Bubble3D,
        Column,
        ColumnStacked,
        Column100PercentStacked,
        Column3D,
        Column3DClustered,
        Column3DStacked,
        Column3D100PercentStacked,
        Cone,
        ConeStacked,
        Cone100PercentStacked,
        ConicalBar,
        ConicalBarStacked,
        ConicalBar100PercentStacked,
        ConicalColumn3D,
        Cylinder,
        CylinderStacked,
        Cylinder100PercentStacked,
        CylindricalBar,
        CylindricalBarStacked,
        CylindricalBar100PercentStacked,
        CylindricalColumn3D,
        Doughnut,
        DoughnutExploded,
        Line,
        LineStacked,
        Line100PercentStacked,
        LineWithDataMarkers,
        LineStackedWithDataMarkers,
        Line100PercentStackedWithDataMarkers,
        Line3D,
        Pie,
        Pie3D,
        PiePie,
        PieExploded,
        Pie3DExploded,
        PieBar,
        Pyramid,
        PyramidStacked,
        Pyramid100PercentStacked,
        PyramidBar,
        PyramidBarStacked,
        PyramidBar100PercentStacked,
        PyramidColumn3D,
        Radar,
        RadarWithDataMarkers,
        RadarFilled,
        Scatter,
        ScatterConnectedByCurvesWithDataMarker,
        ScatterConnectedByCurvesWithoutDataMarker,
        ScatterConnectedByLinesWithDataMarker,
        ScatterConnectedByLinesWithoutDataMarker,
        StockHighLowClose,
        StockOpenHighLowClose,
        StockVolumeHighLowClose,
        StockVolumeOpenHighLowClose,
        Surface3D,
        SurfaceWireframe3D,
        SurfaceContour,
        SurfaceContourWireframe
    }

    public enum WorksheetExportFormat
    {
        Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum WorkbookExportFormat
    {
        Csv, Xlsx, Xlsm, Xltx, Xltm, Text, Html, Pdf, Ods, Xls, Spreadsheetml, Xlsb, Xps, Tiff
    }

    public enum CellsSplitFormat
    {
        Csv, Xlsx, Xlsm, Xltx, Xltm, Text, Html, Pdf, Ods, Xls, Spreadsheetml, Xlsb, Xps, Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum CellsPictureFormat
    {
        Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum CellsOleObjectFormat
    {
        Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum CellsProtectionType
    {
        All, Contents, Objects, Scenarios
    }

    public enum WorkbookEncryptionType
    {
        XOR, EnhancedCryptographicProviderV1, StrongCryptographicProvider
    }
}
