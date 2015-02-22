package com.android.alaa.financeapp.utilities;// DirectoryChooserDialog.java
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.text.Editable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DirectoryChooserDialog
{
    private boolean mIsNewFolderEnabled = true;
    private String mSdcardDirectory = "/";
    private Context mContext;
    private TextView mTitleView;
    private String mDir = "";
    private List<String> mSubdirs = null;
    private ChosenDirectoryListener mChosenDirectoryListener = null;
    private ArrayAdapter<String> mListAdapter = null;


    // Callback interface for selected directory
    public interface ChosenDirectoryListener
    {
        public void onChosenDir(String chosenDir);
    }

    public DirectoryChooserDialog(Context mContext, ChosenDirectoryListener mChosenDirectoryListener)
    {
        this.mContext = mContext;
        this.mChosenDirectoryListener = mChosenDirectoryListener;
    }


    // setNewFolderEnabled() - enable/disable new folder button

    public void setNewFolderEnabled(boolean isNewFolderEnabled)
    {
        mIsNewFolderEnabled = isNewFolderEnabled;
    }

    public boolean getNewFolderEnabled()
    {
        return mIsNewFolderEnabled;
    }

    // chooseDirectory() - load directory chooser dialog for initial
    // default sdcard directory

    public void chooseDirectory()
    {
        // Initial directory is sdcard directory
        chooseDirectory(mSdcardDirectory);
    }

    // chooseDirectory(String dir) - load directory chooser dialog for initial
    // input 'dir' directory

    public void chooseDirectory(String dir)
    {
        File dirFile = new File(dir);
        if (! dirFile.exists() || ! dirFile.isDirectory())
        {
            dir = mSdcardDirectory;
        }

        mDir = dir;
        mSubdirs = getDirectories(dir);

        class DirectoryOnClickListener implements DialogInterface.OnClickListener
        {
            public void onClick(DialogInterface dialog, int item)
            {
                // Navigate into the sub-directory

                String selected = ((AlertDialog) dialog).getListView().getAdapter().getItem(item).toString();

                if (selected.equals(".."))
                    mDir = new File (mDir).getParent();
                else {
                    mDir += selected + "/";
                }
                updateDirectory();
            }
        }

        AlertDialog.Builder dialogBuilder =
                createDirectoryChooserDialog(dir, mSubdirs, new DirectoryOnClickListener());

        dialogBuilder.setPositiveButton("OK", new OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                // Current directory chosen
                if (mChosenDirectoryListener != null)
                {
                    // Call registered listener supplied with the chosen directory
                    mChosenDirectoryListener.onChosenDir(mDir);
                }
            }
        }).setNegativeButton("Cancel", null);

        final AlertDialog dirsDialog = dialogBuilder.create();
        dirsDialog.setCanceledOnTouchOutside(true);

        dirsDialog.show();
    }

    private boolean createSubDir(String newDir)
    {
        File newDirFile = new File(newDir);
        if (! newDirFile.exists() )
        {
            return newDirFile.mkdir();
        }

        return false;
    }

    private List<String> getDirectories(String dir)
    {
        List<String> dirs = new ArrayList<String>();

        try
        {
            File dirFile = new File(dir);
            if (! dirFile.exists() || ! dirFile.isDirectory())
            {
                return dirs;
            }

            if(!dirFile.getPath().equals(mSdcardDirectory))
                dirs.add("..");

            for (File file : dirFile.listFiles())
            {
                if(file.isDirectory() || file.getName().contains(".qif"))
                    dirs.add( file.getName() );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Collections.sort(dirs, new Comparator<String>()
        {
            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
            }
        });

        return dirs;
    }

    private AlertDialog.Builder createDirectoryChooserDialog(String title, List<String> listItems,
                                                             DialogInterface.OnClickListener onClickListener)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);

        // Create custom view for AlertDialog title containing
        // current directory TextView and possible 'New folder' button.
        // Current directory TextView allows long directory path to be wrapped to multiple lines.
        LinearLayout titleLayout = new LinearLayout(mContext);
        titleLayout.setOrientation(LinearLayout.VERTICAL);

        mTitleView = new TextView(mContext);
        mTitleView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        mTitleView.setTextAppearance(mContext, android.R.style.TextAppearance_Large);
        mTitleView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        mTitleView.setText(title);

        Button newDirButton = new Button(mContext);
        newDirButton.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        newDirButton.setText("New folder");
        newDirButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final EditText input = new EditText(mContext);

                // Show new folder name input dialog
                new AlertDialog.Builder(mContext).
                        setTitle("New folder name").
                        setView(input).setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        Editable newDir = input.getText();
                        String newDirName = newDir.toString();
                        // Create new directory
                        if ( createSubDir(mDir + "/" + newDirName) )
                        {
                            // Navigate into the new directory
                            mDir += "/" + newDirName;
                            updateDirectory();
                        }
                        else
                        {
                            Toast.makeText(
                                    mContext, "Failed to create '" + newDirName +
                                            "' folder", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("Cancel", null).show();
            }
        });

        if (!mIsNewFolderEnabled)
        {
            newDirButton.setVisibility(View.GONE);
        }

        titleLayout.addView(mTitleView);
        titleLayout.addView(newDirButton);

        dialogBuilder.setCustomTitle(titleLayout);

        mListAdapter = createListAdapter(listItems);

        dialogBuilder.setSingleChoiceItems(mListAdapter, -1, onClickListener);
        dialogBuilder.setCancelable(false);

        return dialogBuilder;
    }

    private void updateDirectory()
    {
        mSubdirs.clear();
        mSubdirs.addAll(getDirectories(mDir));
        mTitleView.setText(mDir);

        mListAdapter.notifyDataSetChanged();
    }

    private ArrayAdapter<String> createListAdapter(List<String> items)
    {
        return new ArrayAdapter<String>(mContext,
                android.R.layout.select_dialog_item, android.R.id.text1, items)
        {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent)
            {
                View v = super.getView(position, convertView, parent);

                if (v instanceof TextView)
                {
                    // Enable list item (directory) text wrapping
                    TextView tv = (TextView) v;
                    tv.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
                    tv.setEllipsize(null);
                }
                return v;
            }
        };
    }
}