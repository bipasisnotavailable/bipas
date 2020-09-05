package koks.files.impl;

import koks.Koks;
import koks.files.Files;
import koks.modules.Module;
import koks.utilities.value.ColorPicker;
import koks.utilities.value.Value;
import koks.utilities.value.values.BooleanValue;
import koks.utilities.value.values.ModeValue;
import koks.utilities.value.values.NumberValue;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.text.NumberFormat;

/**
 * @author avox | lmao | kroko
 * @created on 05.09.2020 : 01:44
 */
public class settings extends Files {

    public settings() {
        super("settings");
    }

    @Override
    public void writeToFile(FileWriter fileWriter) throws Exception {
        for(Value value : Koks.getKoks().valueManager.getValues()) {
            if(value instanceof BooleanValue) {
                fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((BooleanValue) value).isToggled() + "\n");
            }else if(value instanceof ModeValue) {
                fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((ModeValue) value).getSelectedMode() + "\n");
            }else if(value instanceof NumberValue) {
                if(((NumberValue) value).getMinDefaultValue() != null) {
                    fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((NumberValue) value).getMinDefaultValue() + ":" + ((NumberValue) value).getDefaultValue()+ "\n");
                }else{
                    fileWriter.write(value.getModule().getModuleName() + ":" + value.getName() + ":" + ((NumberValue) value).getDefaultValue()+ "\n");
                }
            }
        }
        fileWriter.close();
    }

    @Override
    public void readFromFile(BufferedReader fileReader) throws Exception {
        String line;
        while((line = fileReader.readLine()) != null) {
            String[] args = line.split(":");
            Module module = Koks.getKoks().moduleManager.getModule(args[0]);
            Value value = Koks.getKoks().valueManager.getValue(module,args[1]);

            if(value instanceof BooleanValue) {
                ((BooleanValue) value).setToggled(Boolean.parseBoolean(args[2]));
            }else if(value instanceof ModeValue) {
                ((ModeValue) value).setSelectedMode(args[2]);
            }else if(value instanceof NumberValue) {
                if(((NumberValue) value).getMinDefaultValue() != null) {
                    ((NumberValue) value).setMinDefaultValue(Float.parseFloat(args[2]));
                    ((NumberValue) value).setDefaultValue(Float.parseFloat(args[3]));
                }else{
                    ((NumberValue) value).setDefaultValue(Float.parseFloat(args[2]));
                }
            }
        }
        fileReader.close();
    }
}