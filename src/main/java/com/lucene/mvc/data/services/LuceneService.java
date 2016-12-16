package com.lucene.mvc.data.services;

import com.lucene.mvc.data.entities.Text;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LuceneService {

	public static final String INDEX_DIRECTORY = "C:\\spring-workspace\\SpringApacheLucene\\IndexDirectory";

	public void createIndex() {
        TextService textService = new TextService();
        List<Text> list = textService.getAllData();
		try {
			Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
			Analyzer standardAnalyzer = new StandardAnalyzer();
			IndexWriterConfig writerConfig = new IndexWriterConfig(standardAnalyzer);
			writerConfig.setOpenMode(OpenMode.CREATE);

			IndexWriter iWriter = new IndexWriter(directory, writerConfig);
			int count = 0;
			Document doc = null;
			Field field = null;
            for (int i = 0; i <list.size() ; i++) {
				doc = new Document();
				field = new StringField("id", String.valueOf(list.get(i).getId()),Field.Store.YES);
				doc.add(field);
				field = new TextField("text", list.get(i).getText(), Field.Store.YES);
				doc.add(field);
				iWriter.addDocument(doc);
                count++;
			}
            System.out.println(count + " record indexed");
			iWriter.commit();
			iWriter.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Text> search(String keyword) {
        long startTime = System.currentTimeMillis();
        List<Text> list = new ArrayList<Text>();
        try {
            IndexReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(INDEX_DIRECTORY)));
			IndexSearcher searcher = new IndexSearcher(directoryReader);
            Analyzer analyzer = new StandardAnalyzer();
            QueryParser titleQP = new QueryParser("text", analyzer);
            Query query = titleQP.parse(keyword);
			TopDocs hits = searcher.search(query,10000000);
            Document doc = null;
			for (int i = 0; i < hits.totalHits; i++) {
				doc = searcher.doc(hits.scoreDocs[i].doc);
                list.add(new Text(doc.get("text"), Integer.valueOf(doc.get("id"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        long endTime = System.currentTimeMillis();
        double seconds =  ((double)(endTime-startTime) / 1000.0) % 60.0 ;
        list.add(new Text(String.valueOf(seconds),0));
        return list;
	}
}
