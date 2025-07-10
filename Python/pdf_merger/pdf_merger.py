import tkinter as tk
from tkinter import filedialog
from PyPDF2 import PdfMerger

root = tk.Tk()
root.withdraw()

file_path = filedialog.askopenfilenames(title="Select PDF Files",filetypes=[("PDF Files","*.pdf")])

if not file_path:
    print("No Files is Selected")
    exit()
merger = PdfMerger()
for pdf in file_path:
    merger.append(pdf)

output_file = filedialog.asksaveasfilename(title="Save PDF Files as",defaultextension=".pdf",filetypes=[("PDF Files","*.pdf")])

if not output_file:
    print("Not Specified PDF Files")
    merger.close()
    exit()

merger.write(output_file)
merger.close()